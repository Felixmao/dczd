package com.dczd.member.services.impl;

import com.dczd.common.command.ValidateTokenCommand;
import com.dczd.common.constant.Constant;
import com.dczd.common.exception.BusinessException;
import com.dczd.common.exception.DczdException;
import com.dczd.common.level.MemberLevel;
import com.dczd.common.result.ApiResultData;
import com.dczd.common.result.ReturnCode;
import com.dczd.common.services.IRedisService;
import com.dczd.common.util.DateUtil;
import com.dczd.common.util.RandomGUID;
import com.dczd.common.util.StringUtil;
import com.dczd.member.command.LoginCommand;
import com.dczd.member.convert.MemberInfoConvert;
import com.dczd.member.entity.login.SmsCheckCoddLogEntity;
import com.dczd.member.entity.member.MemberAppEntity;
import com.dczd.member.entity.member.MemberAppLoginLogEntity;
import com.dczd.member.entity.member.MemberEntity;
import com.dczd.member.mapper.MemberLecturerMapper;
import com.dczd.member.mapper.MemberMapper;
import com.dczd.member.mapper.MemberTeacMapper;
import com.dczd.member.pojo.member.MemberPojo;
import com.dczd.member.pojo.member.MemberTeacPojo;
import com.dczd.member.repository.MemberAppLoginLogRepository;
import com.dczd.member.repository.MemberAppRepository;
import com.dczd.member.repository.MemberRepository;
import com.dczd.member.repository.SmsCheckCoddLogRepository;
import com.dczd.member.services.ILoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * @program: com.dczd.member.services.impl
 * @description: 用户登录实现
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Service(value = "loginService")
@CacheConfig(cacheNames = "memberCache")
@Transactional(rollbackFor = {Exception.class})
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private IRedisService redisService;

    @Autowired
    private SmsCheckCoddLogRepository smsCheckCoddLogRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAppRepository memberAppRepository;

    @Autowired
    private MemberAppLoginLogRepository memberAppLoginLogRepository;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberTeacMapper memberTeacMapper;

    @Resource
    private MemberLecturerMapper memberLecturerMapper;

    @Value("${app.member.info.cache.timeout}")
    private Long appMemberInfoTimeOut;

    @Value("${app.member.info.id.cache.timeout}")
    private Long memberInfoIdTimeOut;

    @Value("${sms.timeout}")
    private Long smsTimeOut;

    @Override
    public ApiResultData iosCheckLogin(LoginCommand command) throws DczdException{
        try{
            MemberEntity userInfo = memberRepository.findByTelephone(command.getTelephone());
            if (StringUtil.isEmpty(userInfo.getUser_Id())){
                //登录
                MemberEntity member = new MemberEntity();
                member.setTelephone(command.getTelephone());
                member.setRegisterTime(DateUtil.paraseDate(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()));
                MemberEntity memberEntity = memberRepository.save(member);

                MemberAppEntity data = new MemberAppEntity();
                BeanUtils.copyProperties(memberEntity,  data);
                data.setPhone_type(command.getPhone_type());
                data.setVersion(command.getVersion());
                data.setDevice(command.getDevice());
                data.setApp_session(new RandomGUID().toString());

                memberAppRepository.save();

            } else {
                //注册
            }

        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            throw new DczdException("登录失败");
        }
        catch (RuntimeException e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * rememberMe 86400*7 = 604800
     * @param member
     */
    private void remember(MemberPojo member){

        MemberAppEntity data = new MemberAppEntity();
        BeanUtils.copyProperties(member,  data);
        /**
         * $__app_data = array();
         *         //为空，就插入一条新的
         *         $__app_data['user_id'] = $user['user_id'];
         *         $__app_data['app_session'] = $user['app_session'];
         *         $__app_data['version'] = $user['version'];
         *         $__app_data['device'] = $user['device'];
         *
         *
         *
         */
    }

    /**
     * 缓存会员信息
     * @param userId
     * @param pojo
     * @throws BusinessException
     */
    private MemberPojo setMemberInfo(int userId, MemberPojo pojo) throws BusinessException{
        try {
            String memberInfoKey = Constant.REDIS_MEMBER_INFO_PREFIX + userId;
            redisService.del(memberInfoKey);
            if (StringUtil.isEmpty(pojo)) {
                MemberPojo sqlPojo = memberMapper.findMemberByUserId(userId);
                redisService.saveObjectToJson(memberInfoKey, sqlPojo, memberInfoIdTimeOut);
                return sqlPojo;
            } else {
                redisService.saveObjectToJson(memberInfoKey, pojo);
                return pojo;
            }
        } catch(JsonProcessingException e){
            e.printStackTrace();
            throw new BusinessException("缓存会员信息失败!");
        }
    }

    /**
     * 缓存app相关信息
     * @param userId
     * @param pojo
     * @throws BusinessException
     */
    private void setAppInfo(int userId, MemberPojo pojo) throws BusinessException {
        try {
            String memberKey = Constant.REDIS_MEMBER_PREFIX + userId;
            MemberPojo memberPojo = redisService.getJson(memberKey, MemberPojo.class);
            if (StringUtil.isNotEmpty(memberPojo) && StringUtil.isNotEmpty(memberPojo.getApp_session())) {
                redisService.del(Constant.REDIS_PREFIX + memberPojo.getApp_session());
            }

            String appSessionKey = Constant.REDIS_PREFIX + pojo.getApp_session();
            redisService.saveObject(appSessionKey, userId);

            redisService.del(memberKey);
            redisService.saveObjectToJson(memberKey, pojo, appMemberInfoTimeOut);
        } catch(JsonProcessingException e){
            e.printStackTrace();
            throw new BusinessException("缓存app相关信息失败!");
        }
    }


    @Override
    public ApiResultData login(LoginCommand command) throws BusinessException {
        ApiResultData resultData = new ApiResultData();

        int id = command.getCheckCodeId();
        String telephone = command.getTelephone();
        SmsCheckCoddLogEntity entity = smsCheckCoddLogRepository.findByIdAndTel(id, telephone);

        //判断验证码是否存在
        if (StringUtil.isEmpty(entity)) {
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg("验证码不存在!");
            return resultData;
        }

        //判断时间是否超过3分钟
        if (DateUtil.diffNowDate(entity.getCreateon()) > smsTimeOut) {
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg("验证码有误或超时已失效!");
            return resultData;
        }

        //验证码错误或者手机号不是接受验证码的手机号
        String checkCode = command.getCheckCode();
        String tel = entity.getTel();
        String code = entity.getCode();
        if (!checkCode.equals(code) || !tel.equals(telephone)) {
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg("验证码错误或者手机号不是接受验证码的手机号!");
            return resultData;
        }

        //通过手机号查找用户信息
        MemberPojo pojo = memberMapper.findMemberByTel(telephone);
        if (StringUtil.isEmpty(pojo)) {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setTelephone(telephone);
            Date now = new Date();
            memberEntity.setRegisterTime(now);
            MemberEntity entity1 = memberRepository.save(memberEntity);
            if (!StringUtil.isEmpty(entity1)) {
                resultData.setCode(ReturnCode.FAILED.code());
                resultData.setMsg("登录失败!");
                return resultData;
            }

            MemberPojo pojoDto = addMemberInfo(command, telephone, now, entity1);

            resultData.setData(MemberInfoConvert.convert(pojoDto));
            return resultData;
        } else {
            updateMemberInfo(command, pojo);

            resultData.setData(MemberInfoConvert.convert(pojo));
        }
        resultData.setCode(ReturnCode.OK.code());
        return resultData;


        try
        {

        }
        catch (DczdException e)
        {
            //log.error(e.getMessage(), e);
        }
        catch (RuntimeException e)
        {
            //log.error(e.getMessage(), e);
        }
        return result;

    }



    /**
     * 无会员信息/新增会员信息
     *
     * @param command
     * @param telephone
     * @param now
     * @param entity1
     * @return
     * @throws JsonProcessingException
     */
    private MemberPojo addMemberInfo(LoginCommand command, String telephone, Date now, MemberEntity entity1)
            throws JsonProcessingException {
        MemberPojo pojoDto = new MemberPojo();
        int userId = entity1.getUserId();
        pojoDto.setUser_id(userId);
        pojoDto.setTelephone(telephone);
        pojoDto.setPhone_type(command.getPhone_type());
        pojoDto.setVersion(command.getVersion());
        pojoDto.setDevice(command.getDevice());
        pojoDto.setRegister_time(now);
        pojoDto.setApp_session(StringUtil.getUUID());
        pojoDto.setAttention(0);
        pojoDto.setIsBuy(false);
        pojoDto.setBinding(false);
        pojoDto.setLevel_id(MemberLevel.BACHELOR.code());
        pojoDto.setNickname("");
        pojoDto.setHeadimgurl("");

        saveMemberInfoAndLog(pojoDto, userId);
        return pojoDto;
    }

    /**
     * 已有信息/更新会员信息
     *
     * @param command
     * @param pojo
     * @throws JsonProcessingException
     * @throws UnsupportedEncodingException
     */
    private void updateMemberInfo(LoginCommand command, MemberPojo pojo) throws JsonProcessingException, UnsupportedEncodingException {
        pojo.setApp_session(StringUtil.getUUID());
        pojo.setPhone_type(command.getPhone_type());
        pojo.setVersion(command.getVersion());
        pojo.setDevice(command.getDevice());

        Integer userId = pojo.getUser_id();
        saveMemberInfoAndLog(pojo, userId);
        if (isJointer(userId)) {
            pojo.setIsBuy(true);
        } else {
            pojo.setIsBuy(checkBuyTopicOrVIP(userId));
        }
        String nickname = pojo.getNickname();
        pojo.setNickname(nickname != null ? URLDecoder.decode(nickname, "utf-8") : "");
        String unionid = pojo.getUnionid();
        pojo.setBinding(!StringUtil.isEmpty(unionid));
        String headimgurl = pojo.getHeadimgurl();
        pojo.setHeadimgurl(StringUtil.isEmpty(headimgurl) ? headimgurl : "");
        pojo.setUnionid(StringUtil.isEmpty(unionid) ? unionid : "");
    }


    /**
     * 购买过专栏或者终身会员（邀请二维码/锁粉/修改为推荐人/）
     *
     * @param userId
     * @return
     */
    private boolean checkBuyTopicOrVIP(Integer userId) {
        int teacId = memberTeacMapper.findTeacIdByUserId(userId, 1);
        if (teacId > 0) {
            return true;
        }
        int lecturerId = memberLecturerMapper.findLecturerIdByUserId(userId, 1, 1);
        return lecturerId > 0;
    }

    /**
     * 是否是联合发起人
     *
     * @param userId
     */
    private boolean isJointer(int userId) throws JsonProcessingException {
        MemberPojo pojo = getMemberInfo(userId);
        Integer levelId = pojo.getLevel_id();
        if (levelId == MemberLevel.COSPONSOR.code() || levelId == MemberLevel.ORIGINAL_PROMOTER.code()) {
            return true;
        }
        return false;
    }

    /**
     * 获取会员信息,缓存无法获取时,查询数据库
     *
     * @param userId
     * @return
     * @throws JsonProcessingException
     */
    private MemberPojo getMemberInfo(int userId) throws JsonProcessingException {
        if (userId <= 0) {
            return null;
        }
        String memberInfoKey = Constant.REDIS_MEMBER_INFO_PREFIX + userId;
        MemberPojo pojo = redisService.getJson(memberInfoKey, MemberPojo.class);
        if (StringUtil.isEmpty(pojo)) {
            return getAndCacheMemberInfoById(userId, pojo);
        }
        return pojo;
    }

    /**
     * 保存会员信息和登录日志信息
     *
     * @param pojoDto
     * @param userId
     * @throws JsonProcessingException
     */
    private void saveMemberInfoAndLog(MemberPojo pojoDto, int userId) throws BusinessException {
        MemberAppEntity memberAppEntity = memberAppRepository.findByUserId(userId);
        if (StringUtil.isEmpty(memberAppEntity)) {
            // 新增
            MemberAppEntity saveAppEntity = new MemberAppEntity();
            saveMemberApp(pojoDto, saveAppEntity);
        } else {
            //更新
            saveMemberApp(pojoDto, memberAppEntity);
        }
        //记录登录日志
        saveAppLoginLog(pojoDto);

        //缓存信息
        setMemberInfo(userId, pojoDto);
        setAppInfo(userId, pojoDto);
    }



    /**
     * 记录app登录日志
     *
     * @param pojo
     */
    private void saveAppLoginLog(MemberPojo pojo) {
        MemberAppLoginLogEntity appLoginLogEntity = new MemberAppLoginLogEntity();
        appLoginLogEntity.setLogin_time(new Date());
        appLoginLogEntity.setPhone_type(pojo.getPhone_type());
        appLoginLogEntity.setUser_id(pojo.getUser_id());
        memberAppLoginLogRepository.save(appLoginLogEntity);
    }

    /**
     * 更新或新增MemberAPP
     *
     * @param pojo
     * @param saveAppEntity
     */
    private void saveMemberApp(MemberPojo pojo, MemberAppEntity saveAppEntity) {
        saveAppEntity.setUserId(pojo.getUser_id());
        saveAppEntity.setApp_session(pojo.getApp_session());
        saveAppEntity.setDevice(pojo.getDevice());
        saveAppEntity.setVersion(pojo.getVersion());
        memberAppRepository.save(saveAppEntity);
    }


    @Override
    public ApiResultData validateToken(ValidateTokenCommand command) throws Exception {
        ApiResultData data = new ApiResultData();
        String tokenKey = Constant.REDIS_PREFIX + command.getToken();
        Integer userId = redisService.getObject(tokenKey);
        if (null != userId) {
            String memberKey = Constant.REDIS_MEMBER_PREFIX + userId;
            MemberPojo memberPojo = redisService.getJson(memberKey, MemberPojo.class);
            if (StringUtil.isNotEmpty(memberPojo)) {
                String version = command.getVersion();
                String device = command.getDevice();
                if (StringUtil.isNotEmpty(version) && StringUtil.isNotEmpty(device)) {
                    memberMapper.updateVersionAndDevice(version, device, userId);
                    data.setCode(memberPojo.getUser_id());
                    return data;
                }
            }
        }
        data.setMsg("您的账号已经在其他设备上登录了，请重新登录！");
        return data;
    }

    @Override
    public ApiResultData test(ValidateTokenCommand command) {
        getMemberInfo();
        return null;
    }

    @Cacheable(cacheNames = "memberTeac")
    public List<MemberTeacPojo> getMemberInfo() {
        List<MemberTeacPojo> pojo = memberTeacMapper.findAll();
        return pojo;
    }

}

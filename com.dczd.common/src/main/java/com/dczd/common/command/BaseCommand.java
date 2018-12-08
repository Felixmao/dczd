package com.dczd.common.command;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 主介入类
 * @author: hou yangkun
 * @create: 2018/11/28
 */
public abstract class BaseCommand {

    /**
     * 当前会员的token
     */
    @ApiModelProperty(value = "当前会员的token")
    private String token;

    /**
     * 操作系统类型（安卓：A，苹果：I）
     */
    @ApiModelProperty(value = "操作系统类型（安卓：A，苹果：I）")
    private String phone_type;

    /**
     * 版本
     */
    @ApiModelProperty(value = "版本")
    private String version;

    /**
     * 设备
     */
    @ApiModelProperty(value = "设备")
    private String device;

    /**
     * 用户身份令牌.
     */
    @ApiModelProperty(value = "用户身份令牌")
    private Integer user_id;


    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序方式
     */
    private Integer sortType;

    /**
     * request
     */
    private HttpServletRequest request;

    /**
     *
     */
    private String page_size = "20";

    /**
     *
     */
    private String page_number = "1";

    /**
     *
     */
    private String page_total;

    /**
     * 获取当前登录用户token
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 获取排序字段
     *
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * 设置排序字段
     *
     * @param sort 排序字段
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 获取排序类型
     *
     * @return sortType
     */
    public Integer getSortType() {
        if (sortType == null) {
            this.sortType = 1;
        }
        return sortType;
    }

    /**
     * 设置排序类型
     *
     * @param sortType 排序类型
     */
    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public String getPage_size() {
        if (StringUtils.isEmpty(page_size)) {
            page_size = "20";
        }
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getPage_number() {
        if (StringUtils.isEmpty(page_number)) {
            return "1";
        }
        return page_number;
    }

    public void setPage_number(String page_number) {
        this.page_number = page_number;
    }

    public String getPage_total() {
        return page_total;
    }

    public void setPage_total(String page_total) {
        this.page_total = page_total;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 用来判断是否需要Token校验
     */
    public boolean validateToken() {
        return true;
    }
}

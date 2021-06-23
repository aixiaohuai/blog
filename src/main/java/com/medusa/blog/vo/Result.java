package com.medusa.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 符合layui form提交返回数据
 * 表单提交已经在index.js做了封装
 */
@Data
public class Result implements Serializable {

    /**
     * 0成功，-1失败
     */
    private int status;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 请求成功回调地址，这个必须设置表单提交alert
     */
    private String action;

    public static Result success() {
        return Result.success("操作成功", null);
    }

    public static Result success(Object data) {
        return Result.success("操作成功", data);
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.status = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.status = -1;
        result.data = null;
        result.msg = msg;
        return result;
    }

    public Result action(String action){
        this.action = action;
        return this;
    }



}

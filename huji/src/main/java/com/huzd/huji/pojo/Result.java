package com.huzd.huji.pojo;

public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERR = "401";
    public static final String SYS_ERR = "500";
    public static final String CODE_ERR = "400";

    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(CODE_SUCCESS,"请求成功",null);
    }
    public static Result success(String meg){
        return new Result(CODE_SUCCESS,meg,null);
    }
    public static Result success(Object data){
        return new Result(CODE_SUCCESS,"请求成功",data);
    }

    public static Result err(){
        return new Result(CODE_ERR,"请求失败",null);
    }
    public static Result err(String meg){
        return new Result(CODE_ERR,meg,null);
    }

}


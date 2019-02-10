package com.miracle.cloud.bean.common;

public class Response<T> {

    private Integer code;
    private String msg;
    private T data;

    public Response() {

    }

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response<Object> success() {
        return new Response<Object>(200, "success", null);
    }


    public static Response<Object> success(Object data) {
        Response<Object> response = new Response<Object>();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    public static Response<Object> error() {
        return new Response<Object>(404, "为服务内部错误!", null);
    }


    public static Response<Object> error(Object data) {
        return new Response<Object>(404, "为服务内部错误！", null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

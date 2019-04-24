package com.ocean.mongo;

public class ResultObject {

    private int code;

    private String desc;

    private Object data;

    public ResultObject() {

    }

    public ResultObject(int code) {
        this.code = code;
    }

    public ResultObject(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.yed.glhf.common.rpc;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class RpcResult<T> implements Serializable {
    private String code;
    private T data;
    private String message;
    private String error;

    public RpcResult() {
    }

    public RpcResult(T data) {
        this.code = "0";
        this.data = data;
    }

    public RpcResult(T data, String message) {
        this.code = "0";
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

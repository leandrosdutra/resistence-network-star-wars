package com.resistence.network.domain.exceptions;

public class StandardError {

    private final Integer status;
    private final String msg;
    private final Long timeStamp;

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() { return status; }

    public String getMsg() { return msg; }

    public Long getTimeStamp() { return timeStamp; }

}
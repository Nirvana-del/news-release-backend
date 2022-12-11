package com.wq.util;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;

@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int SUCCESS = 200;
    private static final int FAILED = 500;
    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Map data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(Map data) {
        return new Result(SUCCESS, "success", data);
    }

    public static Result error(Map data) {
        return new Result(FAILED, "error", data);
    }
}
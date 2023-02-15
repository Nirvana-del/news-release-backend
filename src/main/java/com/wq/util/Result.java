package com.wq.util;

import java.io.Serializable;
import lombok.Data;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int SUCCESS = 200;
    private static final int FAILED = 500;
    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(SUCCESS, "success", data);
    }

    public static <T> Result<T> error(T data) { return new Result<T>(FAILED, "error", data); }
}
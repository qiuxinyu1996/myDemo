package com.qiuxinyu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String status;
    private Object data;
    private String message;

    public static Result success(Object data) {
        return new Result("200", data, "success");
    }

    public static Result success(String message) {
        return new Result("200", null, message);
    }

    public static Result success(String message,Object data) {
        return new Result("200", data, message);
    }

    public static Result success() {
        return new Result("200", null, null);
    }

    public static Result fail(String status, String message) {
        return new Result(status, null, message);
    }

    public static Result fail(String status) {
        return new Result(status, null, null);
    }
}

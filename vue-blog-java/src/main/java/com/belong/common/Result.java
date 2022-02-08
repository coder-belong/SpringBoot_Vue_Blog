package com.belong.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.belong.common.Result;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    // 成功的数据响应
    public static Result success(Object data) {
        Result result = new Result("200", "操作成功", data);
        return result;
    }

    public static Result success(String code, String msg,Object data) {
        Result result = new Result(code, msg, data);
        return result;
    }

    // 失败的数据响应
    public static Result fail(String msg) {
        Result result = new Result("500", msg, null);
        return result;
    }

    public static Result fail(String code, String msg,Object data) {
        Result result = new Result(code, msg, data);
        return result;
    }
}

package com.example.demo.utill;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;

    private String msg;

    private Object data;

    public Result() {
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.msg= message;
        this.data = data;
    }
    public Result(int code, String message) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public  static  Result success(String message, Object data){

        return  new Result(0,message,data);
    }
    public  static  Result success(String message){

        return  new Result(0,message);
    }

    public static Result fail(String message){

        return new Result(1,message);
    }


}

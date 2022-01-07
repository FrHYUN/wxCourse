package com.example.demo.utill;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private boolean flag;

    private String message;

    private Object data;

    public Result() {
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public  static  Result success(String message, Object data){

        return  new Result(true,message,data);
    }
    public  static  Result success(String message){

        return  new Result(true,message);
    }

    public static Result fail(String message){

        return new Result(false,message);
    }


}

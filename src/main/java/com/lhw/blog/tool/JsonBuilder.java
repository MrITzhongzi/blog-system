package com.lhw.blog.tool;

import javax.swing.*;

/**
 * @description: 控制返回json格式
 * @author: lihongwei
 * @time: 2020/4/8 1:58 下午
 */
public class JsonBuilder {

    public JsonBuilder(){

    }

    public JsonBuilder(int code) {
        this.code = code;
    }

    public JsonBuilder(int code, String description){
        this.code = code;
        this.description = description;
    }

    public JsonBuilder(int code, String description, Object data){
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public int code;

    public String description;

    public Object data;


    public static JsonBuilder buildSuccess(){
        return new JsonBuilder(0, "success", null);
    }

    public static JsonBuilder buildSuccess(int code){
        return new JsonBuilder(code, "success", null);
    }

    public static JsonBuilder buildSuccess(String description){
        return new JsonBuilder(0, description, null);
    }

    public static JsonBuilder buildSuccess(Object data){
        return new JsonBuilder(0, "success", data);
    }

    public static JsonBuilder buildSuccess(int code, String description){
        return new JsonBuilder(code, description, null);
    }

    public static JsonBuilder buildSuccess(int code, Object data){
        return new JsonBuilder(code, "success", data);
    }

    public static JsonBuilder buildSuccess(String description, Object data){
        return new JsonBuilder(0, description, data);
    }

    public static JsonBuilder buildSuccess(int code, String description, Object data){
        return new JsonBuilder(code, description, data);
    }

    public static JsonBuilder buildError(){
        return new JsonBuilder(-1, "error", null);
    }

    public static JsonBuilder buildError(int code){
        return new JsonBuilder(code, "error", null);
    }

    public static JsonBuilder buildError(String description){
        return new JsonBuilder(-1, description, null);
    }

    public static JsonBuilder buildError(Object data){
        return new JsonBuilder(-1, "error", data);
    }

    public static JsonBuilder buildError(int code, String description){
        return new JsonBuilder(code, description, null);
    }

    public static JsonBuilder buildError(int code, Object data){
        return new JsonBuilder(code, "error", data);
    }

    public static JsonBuilder buildError(String description, Object data){
        return new JsonBuilder(-1, description, data);
    }

    public static JsonBuilder buildError(int code, String description, Object data){
        return new JsonBuilder(code, description, data);
    }


}

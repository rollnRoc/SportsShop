/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.core.utilities.results;

/**
 *
 * @author Emre Yıldırım
 */
public class Result {
    public boolean success;
    public String message;
    public Result(boolean success){
        this.success = success;
    }
    public Result(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    
}

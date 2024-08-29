/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.core.utilities.results;

/**
 *
 * @author Emre Yıldırım
 */
public class SuccessDataResult<T> extends DataResult {
    public SuccessDataResult(T data, String message){
        super(data,true,message);
    }
    public SuccessDataResult(T data){
        super(data,true);
    }
    public SuccessDataResult(){
        super(null,true);
    }
    public SuccessDataResult(String message){
        super(null,true,message);
    }
}

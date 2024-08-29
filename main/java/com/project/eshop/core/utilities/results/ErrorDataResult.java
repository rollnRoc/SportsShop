/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.core.utilities.results;

/**
 *
 * @author Emre Yıldırım
 */
public class ErrorDataResult<T> extends DataResult {
    public ErrorDataResult(T data, String message){
        super(data,false,message);
    }
    public ErrorDataResult(T data){
        super(data,false);
    }
    public ErrorDataResult(){
        super(null,false);
    }
    public ErrorDataResult(String message){
        super(null,false,message);
    }
}

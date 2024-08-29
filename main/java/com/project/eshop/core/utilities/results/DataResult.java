/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.core.utilities.results;

/**
 *
 * @author Emre Yıldırım
 */
public class DataResult<T> extends Result {
    private T data;
    public DataResult(T data, boolean success, String message){
        super(success,message);
        this.data = data;
    }
    public DataResult(T data, boolean success){
        super(success);
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
}

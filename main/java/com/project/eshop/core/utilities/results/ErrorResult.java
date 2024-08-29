/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.core.utilities.results;

/**
 *
 * @author Emre Yıldırım
 */

    public class ErrorResult extends Result {

        public ErrorResult() {
            super(false);
        }

        public ErrorResult(String message) {
            super(false, message);
        }
}

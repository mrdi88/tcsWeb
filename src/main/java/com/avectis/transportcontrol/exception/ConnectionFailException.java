/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.exception;

/**
 *
 * @author Ivan
 */
public class ConnectionFailException extends Exception {
    public ConnectionFailException(String message){
        super(message);
    }
}

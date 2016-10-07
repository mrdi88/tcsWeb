/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.util;

/**
 *
 * @author Dima
 */
public enum QueueType {  
    BUFFER, INFO, DOCK;  
    public static String getQueueTypeName(QueueType type){  
	switch (type){  
	   case BUFFER : return "Buffer";  
	   case INFO : return "Info";  
           case DOCK : return "Dock";  
	   default : return null;  
	}  
    }  
}
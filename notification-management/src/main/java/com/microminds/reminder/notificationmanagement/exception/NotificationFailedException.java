package com.microminds.reminder.notificationmanagement.exception;

public class NotificationFailedException extends RuntimeException {

    public NotificationFailedException(){
        super("unable to notify!!!");
    }
    public NotificationFailedException(String message){
        super(message);
    }

}

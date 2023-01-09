package com.example.Library.service;

import java.util.UUID;

public class ServiceUtils {

    public static UUID formatUuid(String accId){

        accId = accId.replace("-","");
        String formatted = String.format(
                accId.substring(0,8)+"-"+
                        accId.substring(8,12)+"-"+
                        accId.substring(12,16)+"-"+
                        accId.substring(16,20)+"-"+
                        accId.substring(20,32)
        );
        return UUID.fromString(formatted);
    }
}

package com.example.secondspring1.error;

import java.util.UUID;

public class ApiBaseException extends RuntimeException {
    private final UUID errorid;
    public ApiBaseException(String massage){
        super(massage);
        this.errorid = UUID.randomUUID();


    }

    public UUID getErrorId() {
        return errorid;
    }
}

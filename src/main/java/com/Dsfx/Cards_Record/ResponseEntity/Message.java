package com.Dsfx.Cards_Record.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
    private String message;
    public Message(String message) {
        this.message = message;
    }
}

package com.quasarfire.quasar.entities;

import lombok.Getter;
import lombok.Setter;

public class TopSecretOut {

    @Getter @Setter
    private Location position;

    @Getter @Setter
    private String message;

    public TopSecretOut(Location position, String message){

        this.position= position;
        this.message= message;
    }

}

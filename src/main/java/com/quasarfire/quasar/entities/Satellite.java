package com.quasarfire.quasar.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Satellite {

    @Getter
    @Setter
    private String name;

    @Getter @Setter
    private double distance;

    @Getter @Setter
    private List<String> message;

}

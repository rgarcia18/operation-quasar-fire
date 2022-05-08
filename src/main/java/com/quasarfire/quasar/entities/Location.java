package com.quasarfire.quasar.entities;

import lombok.Getter;
import lombok.Setter;

public class Location {

    @Getter @Setter
    private double x;

    @Getter @Setter
    private double y;

    public Location(double[] positions){
        this.x= positions[0];
        this.y= positions[1];
    }
}

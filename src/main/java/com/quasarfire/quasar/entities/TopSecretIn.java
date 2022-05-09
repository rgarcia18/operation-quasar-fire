package com.quasarfire.quasar.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TopSecretIn {

    @Getter @Setter
    private List<Satellite> satellites;

    public List<List<String>> messagesTopSecret() {

        List<List<String>> messageTopSecret = new ArrayList<List<String>>();

        for(Satellite satellite : satellites){
            messageTopSecret.add(satellite.getMessage());
        }

        return messageTopSecret;
    }

    public double[] distancesTopSecret() {

        double[] distances = new double[satellites.size()];

        for(int i = 0; i < satellites.size(); i++){
            distances[i] = satellites.get(i).getDistance();
        }

        return distances;
    }
}

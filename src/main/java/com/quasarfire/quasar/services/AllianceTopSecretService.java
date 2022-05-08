package com.quasarfire.quasar.services;

import com.quasarfire.quasar.entities.Location;
import com.quasarfire.quasar.entities.TopSecretIn;
import com.quasarfire.quasar.entities.TopSecretOut;
import com.quasarfire.quasar.exceptions.MessageException;
import com.quasarfire.quasar.models.AllianceTopSecretModel;
import com.quasarfire.quasar.repositories.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AllianceTopSecretService {

    @Autowired
    SatelliteRepository satelliteRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private LocationService locationService;

    @Autowired
    private MessageService messageService;

    public TopSecretOut inputData(TopSecretIn topSecretIn) throws MessageException{

        int satellitesTotal = Integer.parseInt(environment.getProperty("satellites.total"));

        if(topSecretIn.getSatellites().size() < satellitesTotal){
            throw new MessageException("Error in the number of satellites.");
        }

        if(topSecretIn.messagesTopSecret().size() < satellitesTotal){
            throw new MessageException("Error in the number of messages.");
        }

        if(!messageService.validMessagesLength(topSecretIn.messagesTopSecret())){
            throw new MessageException("Error, the length of the messages is not equal.");
        }

        String message = messageService.getSecretMessage(topSecretIn.messagesTopSecret());
        double [] position = locationService.getLocation(topSecretIn.distancesTopSecret());

        Location positions = new Location(position);

        return new TopSecretOut(positions, message);
    }

    public AllianceTopSecretModel save(AllianceTopSecretModel allianceTopSecret){
        return satelliteRepository.save(allianceTopSecret);
    }
}




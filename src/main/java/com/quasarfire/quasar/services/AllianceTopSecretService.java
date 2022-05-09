package com.quasarfire.quasar.services;

import com.quasarfire.quasar.entities.Location;
import com.quasarfire.quasar.entities.Satellite;
import com.quasarfire.quasar.entities.TopSecretIn;
import com.quasarfire.quasar.entities.TopSecretOut;
import com.quasarfire.quasar.exceptions.MessageException;
import com.quasarfire.quasar.models.AllianceTopSecretModel;
import com.quasarfire.quasar.repositories.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        String message = messageService.getMessage(topSecretIn.messagesTopSecret());
        double [] position = locationService.getLocation(topSecretIn.distancesTopSecret());

        Location positions = new Location(position);

        return new TopSecretOut(positions, message);
    }

    public TopSecretOut inputDataSplit(Satellite satellite, String nameSatellite) throws MessageException{

        String[] satellites = environment.getProperty("satellites.names", String[].class);

        if(!Arrays.asList(satellites).contains(nameSatellite)){
            throw new MessageException("Error, undefined satellite.");
        }

        if(satellite.getMessage().isEmpty()) {
            throw new MessageException("Error, message cannot be empty.");
        }

        if(satellite.getDistance() == 0) {
            throw new MessageException("Error, distance must be specified.");
        }

        AllianceTopSecretModel allianceTopSecretModel =  new AllianceTopSecretModel();
        allianceTopSecretModel.setSatellite(nameSatellite);
        allianceTopSecretModel.setDistance(satellite.getDistance());
        allianceTopSecretModel.setMessage(satellite.getMessage());
        save(allianceTopSecretModel);

        return new TopSecretOut(null, "Message saved successfully.");

    }

    public TopSecretOut inputData() throws MessageException{

        List<AllianceTopSecretModel> allianceTopSecret = (ArrayList<AllianceTopSecretModel>)
                satelliteRepository.getLatestRecords(Integer.parseInt(environment.getProperty("satellites.total")));

        TopSecretIn topSecretIn = new TopSecretIn();
        List<Satellite> satellites = new ArrayList<Satellite>();

        for(AllianceTopSecretModel alliance : allianceTopSecret) {
            Satellite satellite = new Satellite();
            satellite.setName(alliance.getSatellite());
            satellite.setDistance(alliance.getDistance());
            satellite.setMessage(alliance.getMessage());
            satellites.add(satellite);
        }

        topSecretIn.setSatellites(satellites);

        return inputData(topSecretIn);
    }

    public AllianceTopSecretModel save(AllianceTopSecretModel allianceTopSecret){
        return satelliteRepository.save(allianceTopSecret);
    }
}




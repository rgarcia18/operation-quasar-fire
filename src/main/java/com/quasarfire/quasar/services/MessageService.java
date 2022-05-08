package com.quasarfire.quasar.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    public String getSecretMessage(List<List<String>> messages){

        String decryptedMessage = "";

        for(int i= 0; i < messages.get(0).size(); i++) {
            for(List<String> message : messages) {
                if(! message.get(i).isEmpty()){
                    decryptedMessage += message.get(i) + " ";
                    break;
                }
            }
        }

        return decryptedMessage;
    }

    public boolean validMessagesLength(List<List<String>> messages){

        int size = 0;
        int j = 0;

        for(int i= 0; i < messages.size(); i++) {
            for(List<String> message : messages) {
                if(j != 0 && size != message.size()){
                    return false;
                }
                j++;
                size = message.size();
            }
        }

        return true;
    }
}

package com.quasarfire.quasar.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    public String getMessage(List<List<String>> messages){

        String msg = "";

        for(int i= 0; i < messages.get(0).size(); i++) {
            for(List<String> message : messages) {
                if(! message.get(i).isEmpty()){
                    msg += message.get(i) + " ";
                    break;
                }
            }
        }

        return msg.trim();
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

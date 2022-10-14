package com.usa.ciclo3.reto.usa.service;

import com.usa.ciclo3.reto.usa.model.Message;
import com.usa.ciclo3.reto.usa.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessages(int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message message){
        if (message.getIdMessage()==null){
            return messageRepository.save(message);
        }else{
            Optional<Message> Maux=messageRepository.getMessage(message.getIdMessage());
            if (Maux.isEmpty()){
                return messageRepository.save(message);
            }else{
                return message;
            }
        }
    }

    public Message update(Message message){
        if (message.getIdMessage() != null){
            Optional<Message> m= messageRepository.getMessage(message.getIdMessage());
            if (m.isEmpty()){
                if (message.getMessageText() != null){
                    m.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(m.get());
                return m.get();
            }else{
                return message;
            }
        }else {
            return message;
        }
    }

    public boolean deleteMessage(int id){
            Boolean dM = getMessages(id).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return dM;
    }

}

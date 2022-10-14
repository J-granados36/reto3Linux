package com.usa.ciclo3.reto.usa.service;


import com.usa.ciclo3.reto.usa.model.Category;
import com.usa.ciclo3.reto.usa.model.Client;
import com.usa.ciclo3.reto.usa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save(Client client){
        if (client.getIdClient()==null){
            return clientRepository.save(client);
        }else{
            Optional<Client> Claux=clientRepository.getClient(client.getIdClient());
            if (Claux.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> Cl = clientRepository.getClient(client.getIdClient());
            if (!Cl.isEmpty()) {
                if (client.getEmail() != null) {
                    Cl.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    Cl.get().setPassword(client.getPassword());
                }
                if (client.getName() != null) {
                    Cl.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    Cl.get().setAge(client.getAge());
                }
                return clientRepository.save(Cl.get());
            }
        }
        return client;
    }



    public boolean deleteClient(int id){
        Boolean dCl = getClient(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return dCl;
    }

}

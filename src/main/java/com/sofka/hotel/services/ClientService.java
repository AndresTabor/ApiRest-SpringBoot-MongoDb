package com.sofka.hotel.services;


import com.sofka.hotel.models.Client;
import com.sofka.hotel.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;



@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Flux<Client> getAllClients(){
        return clientRepository.findAll().log().delayElements(Duration.ofSeconds(1));
    }

    public Flux<Client> getAllClientWithLiminit(int limitRequest){
        Flux<Client> clientFlux = clientRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
        return clientFlux.limitRate(limitRequest);
    }

    public Mono<Client> findById(String id){
        return clientRepository.findById(id);
    }

    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client).log();
    }

    public Mono<ResponseEntity<Client>> updateClient(String id, Client client) {
        return clientRepository.findById(id)
                .flatMap(oldClient -> {
                    oldClient.setName(client.getName());
                    oldClient.setSurName(client.getSurName());
                    oldClient.setDocumentType(client.getDocumentType());
                    oldClient.setDocument(client.getDocument());
                    oldClient.setEmail(client.getEmail());
                    oldClient.setComesFrom(client.getComesFrom());
                    oldClient.setDate(client.getDate());
                    oldClient.setRoom(client.getRoom());
                    oldClient.setPrice(client.getPrice());
                    oldClient.setState(client.getState());
                    return clientRepository.save(oldClient);
                })
                .map(updatedClient -> new ResponseEntity<>(updatedClient, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    public Mono<Client> deleteClientById(String id) {
        return clientRepository.findById(id)
                .flatMap(deletedClient -> clientRepository.delete(deletedClient)
                        .then(Mono.just(deletedClient)));
    }
}

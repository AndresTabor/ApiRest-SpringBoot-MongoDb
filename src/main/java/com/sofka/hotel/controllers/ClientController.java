package com.sofka.hotel.controllers;


import com.sofka.hotel.models.Client;
import com.sofka.hotel.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/")
    public Flux<Client> getAllClients(){
        return clientService.getAllClients().delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "/limit/{nro}")
    public Flux<Client> getAllClientWithLiminit(@PathVariable int nro){
        return clientService.getAllClientWithLiminit(nro);
    }

    @GetMapping("/{id}")
    public Mono<Client> findById(@PathVariable String id){
        return clientService.findById(id);
    }

    @PostMapping("/")
    public Mono<Client> createClient(@Valid @RequestBody Client client) {
        return clientService.createClient(client).log();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Client>>  updateClient(@Valid @PathVariable String id , @RequestBody Client client) {
        return clientService.updateClient(id,client);

    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteClientById(@PathVariable String id){
        return clientService.deleteClientById(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
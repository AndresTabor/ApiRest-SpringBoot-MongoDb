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

/**
 * Controlador que recibe las solicitudes HTTP del cliente a la Api
 */
@RestController
@RequestMapping("/api/client")
public class ClientController {
    /**
     * Clase ClientService
     */
    private final ClientService clientService;

    /**
     * Constructor del controlador
     * @param clientService servicio del cliente que se comunica con la DB
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Metodo para obtener todos los clientes creados
     * @return Un flujo de datos de clientes
     */
    @GetMapping(value = "/")
    public Flux<Client> getAllClients(){
        return clientService.getAllClients().delayElements(Duration.ofSeconds(1));
    }

    /**
     * Metodo para obtener todos los clientes con un limite en el flujo de datos
     * @param nro Numero que limita el flujo de datos
     * @return Un flujo de datos de clientes
     */
    @GetMapping(value = "/limit/{nro}")
    public Flux<Client> getAllClientWithLiminit(@PathVariable int nro){
        return clientService.getAllClientWithLiminit(nro);
    }

    /**
     * Metodo para obtener un cliente por su id
     * @param id Identificador de cliente
     * @return Un unico cliente que concuerda con el Id recibido en la url
     */
    @GetMapping("/{id}")
    public Mono<Client> findById(@PathVariable String id){
        return clientService.findById(id);
    }

    /**
     * Metodo para crear un cliente nuevo
     * @param client Un objeto cliente proveniente del body de la peticion Post
     * @return El cliente creado
     */
    @PostMapping("/")
    public Mono<Client> createClient(@Valid @RequestBody Client client) {
        return clientService.createClient(client).log();
    }

    /**
     * Metodo para actualizar un cliente existente
     * @param id Identificador del cliente a actualizar
     * @param client Un objeto cliente proveniente del body de la peticion Post
     * @return El cliente actualizado
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Client>>  updateClient(@Valid @PathVariable String id , @RequestBody Client client) {
        return clientService.updateClient(id,client);

    }

    /**
     * Metodo para eliminar un cliente
     * @param id Identificador del cliente a eliminar
     * @return Una respuesta o mensaje 
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteClientById(@PathVariable String id){
        return clientService.deleteClientById(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
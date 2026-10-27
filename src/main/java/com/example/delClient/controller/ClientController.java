package com.example.delClient.controller;

import com.example.delClient.dto.ClientCreateRequest;
import com.example.delClient.dto.ClientResponse;
import com.example.delClient.dto.ClientUpdateRequest;
import com.example.delClient.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Управление клиентами")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Создать клиента")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse create(
            @Valid @RequestBody ClientCreateRequest req) {
            return clientService.create(req);

    }

    @Operation(summary = "Получить всех клиентов")
    @GetMapping
    public List<ClientResponse> getAll() {
        return clientService.findAll();
    }

    @Operation(summary = "Получить клиента по id")
    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id) {
        return clientService.findById(id);
    }
    @Operation(summary = "Обновить клиента")
    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable Long id, @Valid @RequestBody ClientUpdateRequest req) {
        return clientService.update(id, req);
    }

    @Operation(summary = "Удалиьт клиента")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.delete(id);
    }
}

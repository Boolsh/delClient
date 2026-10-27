package com.example.delClient.service;

import com.example.delClient.dto.ClientCreateRequest;
import com.example.delClient.dto.ClientResponse;
import com.example.delClient.dto.ClientUpdateRequest;
import com.example.delClient.entity.ClientAccount;
import com.example.delClient.entity.ClientStatus;
import com.example.delClient.exception.ClientNotFoundException;
import com.example.delClient.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientResponse create(ClientCreateRequest req) {
        ClientAccount client = new ClientAccount(req.fullName(), normalizeGender(req.gender()));
        client.setStatus(ClientStatus.ACTIVE);
        return toResponse(clientRepository.save(client));
    }

    @Transactional
    public ClientResponse update(Long id, ClientUpdateRequest req) {
        ClientAccount client = getOrErr(id);
        if (req.fullName() != null)
            client.setFullName(req.fullName());
        if (req.gender() != null)
            client.setGender(normalizeGender(req.gender()));
        if (req.status() != null)
            client.setStatus(req.status());
        return toResponse(clientRepository.saveAndFlush(client));
    }

    @Transactional
    public void  delete(Long id) {
        ClientAccount client = getOrErr(id);
        clientRepository.delete(client);
    }

    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    public ClientResponse findById(Long id) {
        return toResponse(getOrErr(id));
    }

    private String normalizeGender(String gender) {
        return gender == null ? null : gender.toUpperCase();
    }
    private ClientAccount getOrErr(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }



    private ClientResponse toResponse(ClientAccount client) {
        return new ClientResponse(client.getId(), client.getFullName(), client.getGender(),
                client.getStatus(), client.getCreateDttm(), client.getModifyDttm());
    }
}

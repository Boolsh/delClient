package com.example.delClient.dto;

import com.example.delClient.entity.ClientStatus;

import java.time.Instant;

public record ClientResponse(
        Long id,
        String fullName,
        String gender,
        ClientStatus status,
        Instant createDttm,
        Instant modifyDttm
) {
}

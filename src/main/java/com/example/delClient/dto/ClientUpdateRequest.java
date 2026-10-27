package com.example.delClient.dto;

import com.example.delClient.entity.ClientGender;
import com.example.delClient.entity.ClientStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientUpdateRequest(
        @Pattern(regexp = ".*\\S.*",
                message = "must not be blank")  String fullName,
        @Pattern(regexp = "[MF]",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "gender nust be M or F") String gender,
        ClientStatus status
) {

}

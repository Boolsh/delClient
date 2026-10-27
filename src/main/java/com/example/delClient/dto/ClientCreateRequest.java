package com.example.delClient.dto;

import com.example.delClient.entity.ClientGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientCreateRequest(
        @NotBlank String fullName,
        @NotBlank
        @Pattern(regexp = "[MF]",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "gender nust be M or F") String gender
) {
}

package com.example.delClient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Entity
@Table(name = "client_account")
@Getter
public class ClientAccount {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "full_name",  nullable = false)
    private String fullName;

    @Setter
//    @Enumerated(EnumType.STRING)
    @Column(name = "gender",  length = 1)
    private String gender;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private ClientStatus status;


    @Column(name = "create_dttm", updatable = false, nullable = false)
    private Instant createDttm;

    @Column(name = "modify_dttm", nullable = false)
    private Instant modifyDttm;

    protected ClientAccount() {};

    public ClientAccount(String fullName, String gender) {
        this.fullName = fullName;
        this.gender = gender;
    }

    @PrePersist
    void onCreate() {
        createDttm =
                modifyDttm =
                        Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        modifyDttm =
                Instant.now();
    }


}

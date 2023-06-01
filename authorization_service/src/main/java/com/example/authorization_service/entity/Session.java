package com.example.authorization_service.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "session_token", nullable = false, length = 255)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private Timestamp expiresAt;
}

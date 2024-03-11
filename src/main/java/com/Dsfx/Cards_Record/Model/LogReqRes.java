package com.Dsfx.Cards_Record.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Table(name = "LOG_REQ_RES")
@NoArgsConstructor
@ToString
public class LogReqRes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long Id;
    @Column(name = "ENDPOINT")
    private String uri;
    @Column(name = "HTTP-METHOD")
    private String httpMethod;
    @Column(name = "REQUEST")
    private String request;
    @Column(name = "RESPONSE")
    private String response;
    @CreationTimestamp
    @Column(name="TIMESTAMP_REQ_RES")
    private LocalDateTime createdOn;
}

package com.Dsfx.Cards_Record.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity

@AllArgsConstructor
@NoArgsConstructor

public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long CardId;
    @Column(length = 100,nullable = false)
    private int BatchId;
    @Column(length = 100,nullable = false)
    private String cardRange;
    @Column(length = 40,nullable = false)
    private int quantityReceived;
    @Column(length = 267,nullable = false)
    private Date ExpiryDate;
    @Column(length = 255,nullable = false)
    private String VendorName;
    @Column(length = 26,nullable = false)
    private Date ReceivedDate;
    @Column(length = 100,nullable = false)
    private String StockStatus;
    @Column(length = 100,nullable = false)
    private String cardType;

}

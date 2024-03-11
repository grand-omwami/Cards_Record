package com.Dsfx.Cards_Record.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDTO {

    private Long Id;
    private Long CardId;
    private int BatchId;
    private String cardRange;
    private int quantityReceived;
    private Date ExpiryDate;
    private String VendorName;
    private Date ReceivedDate;
    private String StockStatus;
    private String cardType;



}


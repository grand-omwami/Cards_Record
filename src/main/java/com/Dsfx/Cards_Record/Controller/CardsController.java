package com.Dsfx.Cards_Record.Controller;

import com.Dsfx.Cards_Record.DTO.CardsDTO;
import com.Dsfx.Cards_Record.Model.Cards;
import com.Dsfx.Cards_Record.Service.CardsService;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cards")

public class CardsController {
@Autowired
  public CardsService cardsService;

    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cards> addCardToInventory(@RequestBody CardsDTO cardsDTO) {
        try {
            Cards addedCard = cardsService.addCardToInventory(cardsDTO);
            return new ResponseEntity<>(addedCard, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CardsDTO>> getAllCards() {

        return ResponseEntity.ok(cardsService.getAllCards());
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Cards> getCardById(@PathVariable Long cardId) throws ChangeSetPersister.NotFoundException {
        Cards card = cardsService.getByCardId(cardId);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PutMapping("/update/{cardId}")
    public ResponseEntity<List<Cards>> updateInventory(@PathVariable Long cardId) {
        try {
            List<Cards> updatedCards = cardsService.updateInventory(cardId);
            return new ResponseEntity<>(updatedCards, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{cardId}")
    public ResponseEntity<List<Cards>> deleteCardsFromInventory(@PathVariable Long cardId) {
        try {
            List<Cards> deletedCards = cardsService.deleteCardsFromInventory(cardId);
            return new ResponseEntity<>(deletedCards, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }








    @PostMapping("/import-excel")
    public ResponseEntity<List<Cards>> importExcelFile(@RequestParam("file") MultipartFile excelFile) throws IOException, ParseException, ParseException, IOException {
        HttpStatus status = HttpStatus.OK;
        List<Cards> cardList = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Row row = worksheet.getRow(index);

                // Assuming your Card entity has appropriate fields
                Cards card = new Cards();
                card.setId((long) row.getCell(0).getNumericCellValue());
                card.setCardId((long) row.getCell(1).getNumericCellValue());
                card.setBatchId((int) row.getCell(2).getNumericCellValue());
                card.setCardRange(row.getCell(3).getStringCellValue());
                card.setQuantityReceived((int) row.getCell(4).getNumericCellValue());

                // Parse Date from String, adjust the date column index accordingly
                Date expiryDate = dateFormat.parse(row.getCell(5).getStringCellValue());
                card.setExpiryDate(expiryDate);

                card.setVendorName(row.getCell(6).getStringCellValue());

                // Parse Date from String, adjust the date column index accordingly
                Date receivedDate = dateFormat.parse(row.getCell(7).getStringCellValue());
                card.setReceivedDate(receivedDate);

                card.setStockStatus(row.getCell(8).getStringCellValue());
                card.setCardType(row.getCell(9).getStringCellValue());

                cardList.add(card);
            }
        }

        // Save the cards to the database using a service or repository
        cardsService.saveAll(cardList);

        return new ResponseEntity<>(cardList, status);
    }
}





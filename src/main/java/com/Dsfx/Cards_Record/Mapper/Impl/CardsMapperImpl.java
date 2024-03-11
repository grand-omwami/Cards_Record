//package com.Dsfx.Cards_Record.Mapper.Impl;
//
//import com.Dsfx.Cards_Record.DTO.CardsDTO;
//import com.Dsfx.Cards_Record.Mapper.CardsMapper;
//import com.Dsfx.Cards_Record.Model.Cards;
//import org.mapstruct.Mapper;
//
//
//
//public class CardsMapperImpl implements CardsMapper {
//
//
//    @Override
//    public CardsDTO modelToDTO(Cards cards) {
//        CardsDTO cardsDTO = new CardsDTO();
//        cardsDTO.setId(cards.getId());
//        cardsDTO.setCardId(cardsDTO.getCardId());
//        cardsDTO.setBatchId(cards.getBatchId());
//        cardsDTO.setCardRange(cards.getCardRange());
//        cardsDTO.setQuantityReceived(cards.getQuantityReceived());
//        cardsDTO.setExpiryDate(cards.getExpiryDate());
//        cardsDTO.setVendorName(cards.getVendorName());
//        cardsDTO.setStockStatus(String.valueOf(cards.getReceivedDate()));
//        cardsDTO.setStockStatus(cards.getStockStatus());
//        cardsDTO.setCardType(cards.getCardType());
//        return cardsDTO;
//    }
//
//    @Override
//    public CardsDTO modelToDTO(CardsDTO cards) {
//        return null;
//    }
//
//    @Override
//    public Cards DTOToModel(CardsDTO cardsDTO) {
//
//        Cards cards = new Cards();
//        cards.setId(cardsDTO.getId());
//        cards.setCardId(cardsDTO.getCardId());
//        cards.setBatchId(cardsDTO.getBatchId());
//        cards.setCardRange(cardsDTO.getCardRange());
//        cards.setQuantityReceived(cardsDTO.getQuantityReceived());
//        cards.setExpiryDate(cardsDTO.getExpiryDate());
//        cards.setVendorName(cardsDTO.getVendorName());
//        cards.setReceivedDate(cardsDTO.getReceivedDate());
//        cards.setStockStatus(cards.getStockStatus());
//        cards.setCardType(cardsDTO.getCardType());
//
//
//        return cards;
//    }
//}

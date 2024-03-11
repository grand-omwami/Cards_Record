package com.Dsfx.Cards_Record.Mapper;

import com.Dsfx.Cards_Record.DTO.CardsDTO;
import com.Dsfx.Cards_Record.Model.Cards;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel ="spring")
public interface CardsMapper {
    CardsMapper INSTANCE = Mappers.getMapper(CardsMapper.class);
    CardsDTO modelToDTO(Cards cards);

    Cards DTOToModel(CardsDTO cardsDTO);

    default CardsDTO modelToDTOWithNullCheck(Cards cards) {
        return cards != null ? modelToDTO(cards) : null;
    }

    default Cards DTOToModelWithNullCheck(CardsDTO cardsDTO) {
        return cardsDTO != null ? DTOToModel(cardsDTO) : null;
    }


}
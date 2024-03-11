package com.Dsfx.Cards_Record.Service;

import com.Dsfx.Cards_Record.DTO.CardsDTO;
import com.Dsfx.Cards_Record.Mapper.CardsMapper;
import com.Dsfx.Cards_Record.Model.Cards;
import com.Dsfx.Cards_Record.Repository.CardsRepository;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CardsService {

    @Autowired
  public CardsRepository cardRepository;

    public CardsService(CardsRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
@Observed(name = "addCard")
    public Cards addCardToInventory(CardsDTO cardsDTO) {
        Cards card = CardsMapper.INSTANCE.DTOToModel(cardsDTO);
        return cardRepository.save(card);
    }
@Observed(name = "get.AllCards")
    public List<CardsDTO> getAllCards() {
        List<Cards> cards = cardRepository.findAll();
        return cards.stream()
                .map(CardsMapper.INSTANCE::modelToDTOWithNullCheck)
                .collect(Collectors.toList()).reversed();
    }
@Observed(name = "get.CardsById")
    public Cards getByCardId(Long cardId) throws ChangeSetPersister.NotFoundException {
        return cardRepository.findById(cardId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
@Observed(name = "updateInventory")
    public List<Cards> updateInventory(Long cardId) throws ChangeSetPersister.NotFoundException {
        Cards existingCard = cardRepository.findById(cardId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Cards updatedCard = cardRepository.save(existingCard);

        return Collections.singletonList(updatedCard);
    }
@Observed(name = "deleteCardsFromInventory")
    public List<Cards> deleteCardsFromInventory(Long cardId) throws ChangeSetPersister.NotFoundException {
        Cards deletedCard = cardRepository.findById(cardId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        cardRepository.delete(deletedCard);

        return Collections.singletonList(deletedCard);
    }
@Observed(name = "SaveExcelFileToRepository")

    public void saveAll(List<Cards> cardList) {
        cardRepository.saveAll(cardList);
    }
}

package com.Dsfx.Cards_Record.Repository;


import com.Dsfx.Cards_Record.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {



}

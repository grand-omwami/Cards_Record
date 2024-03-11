package com.Dsfx.Cards_Record.Repository;

import com.Dsfx.Cards_Record.Model.LogReqRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingDao extends JpaRepository<LogReqRes,Long> {

}

package com.kosenkova.telegrambot.dao;

import com.kosenkova.telegrambot.model.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Long> {

    Optional<List<Answer>> getAllBySentFalse();

    Optional<Answer> getById(Long id);
}

package com.kosenkova.telegrambot.dao;

import com.kosenkova.telegrambot.model.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {
}

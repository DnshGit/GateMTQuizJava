package com.sidwebapps.gatequizapp.repositories;

import com.sidwebapps.gatequizapp.models.QuestionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionInfo, Long> {
    List<QuestionInfo> findQuestionInfoBySectionAndSubSection(String section, String subSection);
}

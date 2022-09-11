package com.sidwebapps.gatequizapp.services;

import com.sidwebapps.gatequizapp.models.QuestionInfo;
import com.sidwebapps.gatequizapp.repositories.QuestionsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class QuizService {

    @Value("${gatequizzapp.file.upload.path}")
    private String uploadPath;

    @Autowired
    private QuestionsRepository questionsRepository;

    public List<QuestionInfo> findAllQuestions() {
        return questionsRepository.findAll();
    }

    public List<QuestionInfo> findBySectionAndSubSection(String section, String subSection) {
        return questionsRepository.findQuestionInfoBySectionAndSubSection(section, subSection);
//                .orElseThrow(() -> new QuestionsNotFoundException(
//                        "Questions not found under" + section + "/" + subSection
//                ));
    }

    public void addQuestion(QuestionInfo questionInfo) {


        questionsRepository.save(questionInfo);
    }

    public void addQuestion(MultipartFile multipartFile) throws IOException {
        FileUtils.writeByteArrayToFile(new File("C:/Users/sirim/Documents/GitHub" + "/" + multipartFile.getOriginalFilename()), multipartFile.getBytes());
        //questionsRepository.save(questionInfo);
    }
}

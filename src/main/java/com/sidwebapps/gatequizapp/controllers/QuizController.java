package com.sidwebapps.gatequizapp.controllers;

import com.sidwebapps.gatequizapp.models.QuestionInfo;
import com.sidwebapps.gatequizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("quiz/{section}/{subSection}")
    public ResponseEntity<List<QuestionInfo>> getQuestions(@PathVariable String section, @PathVariable String subSection) {
        List<QuestionInfo> questions = quizService.findBySectionAndSubSection(section, subSection);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping(value = "add", consumes = {
                                                MediaType.MULTIPART_FORM_DATA_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE})
    public HttpStatus addQuestion(@RequestPart("file") MultipartFile multipartFile, @RequestPart("answer") String answer) throws IOException {

        quizService.addQuestion(multipartFile);
        return HttpStatus.OK;
    }

    @GetMapping("{file}")
    public ResponseEntity<Resource> getFile(@PathVariable String file, HttpServletRequest request) throws Exception {
        Resource resource = this.loadFileAsResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(request.getServletContext().getMimeType(resource.getFile().getAbsolutePath())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);



    }

    public Resource loadFileAsResource(String fileName) throws Exception {


        Path filePath = Path.of("C:/Users/sirim/Documents/GATE App/gate MT Quiz images/aptitude/Aptitude_01", fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;

        }
        return null;
    }


}

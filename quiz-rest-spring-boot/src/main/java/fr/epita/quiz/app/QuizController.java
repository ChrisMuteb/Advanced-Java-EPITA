package fr.epita.quiz.app;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.IQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class QuizController {
    @Autowired
    IQuestionDAO dao;
    @GetMapping("/questions")
    public List<Question> getAllQuestions(){
        return dao.search(new Question());
    }
    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable("id") String id){
        return dao.getById(id);
    }
    @PostMapping(value = "/questions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createQuestion(@RequestBody Question question){
        dao.search(question);
        URI location = URI.create("/questions/" + question.getId());
        return ResponseEntity
                .created(location)
                .build();
    }
}

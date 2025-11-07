package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class CreateQuestionController {

    private final QuestionDao questionDao;

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                0);
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }
}

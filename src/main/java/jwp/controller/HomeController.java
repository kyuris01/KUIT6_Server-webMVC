package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDao;

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Question> questions = questionDao.findAll();
        req.setAttribute("questions", questions);
        return "/home";
    }
}

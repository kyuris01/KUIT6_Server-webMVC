package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDao;

    @RequestMapping("/")
    public String home(Model model) throws Exception {
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);
        return "/home";
    }
}

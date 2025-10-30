package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class CreateQuestionController implements Controller {

    private final QuestionDao questionDao;

    public CreateQuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        if(writer == null || title == null || contents == null) {
            return "/qna/form.jsp";
        }

        Question question = new Question(writer, title, contents, LocalDateTime.now(), 0);

        questionDao.insert(question);
        return "/qna/show.jsp";
    }
}

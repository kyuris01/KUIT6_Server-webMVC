package jwp.controller;

import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaController implements Controller {

    private final QuestionDao questionDao;

    public QnaController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/qna/show.jsp";
    }
}

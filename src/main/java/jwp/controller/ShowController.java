package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller{

    private QuestionDao questionDao;

    public ShowController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = questionDao.findQuestionById(Long.valueOf(req.getParameter("questionId")));
        System.out.println("questionId:"+ question.getQuestionId());
        req.setAttribute("question", question);

        return "/qna/show.jsp";
    }
}

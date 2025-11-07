package jwp.dao;

import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {

    private final EntityManager em;

    @Transactional
    public Question insert(Question question) {
        em.persist(question);
        return em.find(Question.class, question.getQuestionId());
    }

    @Transactional
    public void update(Question question)  {
        em.merge(question);
    }

    public List<Question> findAll() {
        return em.createQuery("select q from Question q", Question.class).getResultList();
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        return em.find(Question.class, questionId);
    }
}

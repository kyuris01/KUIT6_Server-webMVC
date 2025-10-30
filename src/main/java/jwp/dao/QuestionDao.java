package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    KeyHolder holder = new KeyHolder();

    public List<Question> findAll() throws SQLException {

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM questions";
        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question insert(Question question) throws SQLException {
        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";


        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(question.getCreatedDate()));
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        jdbcTemplate.update(sql, pstmtSetter, holder);
        System.out.println("question = " + question.getQuestionId());
        question.setQuestionId((long) holder.getId());
        System.out.println("question = " + question.getQuestionId());
        return question;
    }

    public Question findQuestionById(Long questionId) throws SQLException {
        JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, questionId);
        };
        RowMapper<Question> rowMapper = resultSet -> new Question(
                resultSet.getLong("questionId"),
                resultSet.getString("writer"),
                resultSet.getString("title"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate").toLocalDateTime(),
                resultSet.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }


}

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

    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

    public List<Question> findAll() throws SQLException {

        String sql = "SELECT * FROM questions";
        RowMapper rowMapper = rs -> new Question(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";


        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(question.getCreatedDate()));
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        KeyHolder holder = new KeyHolder();
        jdbcTemplate.update(sql, pstmtSetter, holder);
        System.out.println(holder.getId());
        return question;
    }


}

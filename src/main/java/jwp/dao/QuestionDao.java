package jwp.dao;

import core.jdbc.JdbcTemplate;
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
}

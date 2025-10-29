package jwp.model;

import java.time.LocalDateTime;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private Integer countOfAnswer;

    public Question(String writer, String title, String contents, LocalDateTime createdDate, Integer countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Long getQuestionId() { return questionId; }
    public String getWriter() { return writer; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public int getCountOfAnswer() { return countOfAnswer; }

}

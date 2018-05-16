package home.redis;


import home.entity.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public @Data @NoArgsConstructor class RedisQuestion {

    private Integer questionId;
    private String questionText;
    private String questionImg;
    private Integer lang;
    private String description;
    private String descriptionImg;
    private List<RedisAnswer> answers;
    private RedisTopic topic;
    private RedisAnswer selectAnswer;

    public RedisQuestion(Question question) {
        this.questionId = question.getQuestionId();
        this.questionText = question.getQuestionText();
        this.questionImg = question.getQuestionImg();
        this.lang = question.getLang();
        this.description = question.getDescription();
        this.descriptionImg = question.getDescriptionImg();

        answers = new ArrayList<>();

        RedisAnswer redisAnswer1 = new RedisAnswer(
                question.getAnswers().get(0).getAnswerId(),
                question.getAnswers().get(0).getAnswer(),
                question.getAnswers().get(0).getAnswerImg(),
                question.getAnswers().get(0).getStatus()
        );
        answers.add(redisAnswer1);

        RedisAnswer redisAnswer2 = new RedisAnswer(
                question.getAnswers().get(1).getAnswerId(),
                question.getAnswers().get(1).getAnswer(),
                question.getAnswers().get(1).getAnswerImg(),
                question.getAnswers().get(1).getStatus()
        );
        answers.add(redisAnswer2);

        RedisAnswer redisAnswer3 = new RedisAnswer(
                question.getAnswers().get(2).getAnswerId(),
                question.getAnswers().get(2).getAnswer(),
                question.getAnswers().get(2).getAnswerImg(),
                question.getAnswers().get(2).getStatus()
        );
        answers.add(redisAnswer3);

        RedisAnswer redisAnswer4= new RedisAnswer(
                question.getAnswers().get(3).getAnswerId(),
                question.getAnswers().get(3).getAnswer(),
                question.getAnswers().get(3).getAnswerImg(),
                question.getAnswers().get(3).getStatus()
        );
        answers.add(redisAnswer4);

        this.topic = new RedisTopic(
                question.getTopic().getTopicId(),
                question.getTopic().getTopic(),
                question.getTopic().getRuTopic(),
                question.getTopic().getQuestionConfig()
        );

        this.selectAnswer = question.getSelectAnswer() == null ? null : new RedisAnswer(
                question.getSelectAnswer().getAnswerId(),
                question.getSelectAnswer().getAnswer(),
                question.getSelectAnswer().getAnswerImg(),
                question.getSelectAnswer().getStatus()
        );
    }
}

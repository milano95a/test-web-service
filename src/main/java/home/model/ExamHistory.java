package home.model;

import home.entity.Subject;
import lombok.Data;

public @Data class ExamHistory {
    private int examId;
    private Subject subjectTop;
    private Subject subjectMid;
    private Subject subjectBot;

    private int resultTop;
    private int resultMid;
    private int resultBot;
    private Long date;

    public void incrementResult(Subject subject){
        if(subject.getSubjectId().equals(subjectTop.getSubjectId())){
            resultTop++;
        }else if(subject.getSubjectId().equals(subjectMid.getSubjectId())){
            resultMid++;
        }else if(subject.getSubjectId().equals(subjectBot.getSubjectId())){
            resultBot++;
        }
    }

    public void initializeResults(){
        resultTop = 0;
        resultMid = 0;
        resultBot = 0;
    }
}

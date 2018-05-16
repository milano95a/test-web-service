package home.service;

import home.entity.*;
import home.model.*;
import home.repo.IHistoryRepo;
import home.repo.ISubjectRepo;
import home.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistoryService {

    @Autowired
    IHistoryRepo historyRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    ISubjectRepo subjectRepo;

    public void saveExamHistory(User user, Exam exam){
        int sessionId = getSessionId();
        Subject subjectTop = subjectRepo.findOne(exam.getSubjectTop().getSubjectId());
        Subject subjectMid = subjectRepo.findOne(exam.getSubjectMid().getSubjectId());
        Subject subjectBot = subjectRepo.findOne(exam.getSubjectBot().getSubjectId());

        saveToHistory(user,subjectTop,exam.getQuestionTop(),sessionId,HistoryType.Exam);
        saveToHistory(user,subjectMid,exam.getQuestionMid(),sessionId,HistoryType.Exam);
        saveToHistory(user,subjectBot,exam.getQuestionBot(),sessionId,HistoryType.Exam);
    }

    public void saveToPrep(User user, Prep prep){
        saveToHistory(user,prep.getSubject(),prep.getQuestion(),getSessionId(),HistoryType.Prep);
    }

    public void saveToHistory(User user, Subject subject, List<Question> questions, int sessionId,HistoryType historyType){
        User dbUser = userRepo.findOne(user.getUserId());

        List<History> histories = new ArrayList<>();
        int score = 0;
        for(Question question: questions){
            History history = new History();
            history.setUser(dbUser);
            history.setHistoryType(historyType);
            history.setDate(new Date().getTime());
            history.setSubject(subject);
            history.setQuestion(question);
            if(question.getSelectAnswer() != null){
                history.setMyAnswer(question.getSelectAnswer());
            }
            history.setSessionId(sessionId);
            histories.add(history);
        }

        historyRepo.save(histories);
    }

    public List<History> getExamHistory(String userId){
        return historyRepo.findHistoriesByHistoryTypeAndUser_UserId(HistoryType.Exam,userId);
    }

    public List<History> getPrepHistory(String userId){
        return historyRepo.findHistoriesByHistoryTypeAndUser_UserId(HistoryType.Prep,userId);
    }

//    public void saveExamToHistory(Exam exam, User user){
//        User dbUser = userRepo.findOne(user.getUserId());
//
//        Integer sessionId = getSessionId();
//
//        List<History> histories = new ArrayList<>();
//
//        List<NamedObject> no = new ArrayList<>();
//
//        no = makeHistoryList(exam.getQuestionTop(),dbUser, sessionId);
//        dbUser = (User)no.get(0).getObject();
//        histories.addAll((List<History>)no.get(1).getObject());
//
//        no = makeHistoryList(exam.getQuestionMid(),dbUser, sessionId);
//        dbUser = (User)no.get(0).getObject();
//        histories.addAll((List<History>)no.get(1).getObject());
//
//        no = makeHistoryList(exam.getQuestionBot(),dbUser, sessionId);
//        dbUser = (User)no.get(0).getObject();
//        histories.addAll((List<History>)no.get(1).getObject());
//
//        userRepo.save(dbUser);
//        historyRepo.save(histories);
//    }

//
//    private List<NamedObject> makeHistoryList(List<Question> questions, User user, int sessionId){
//        List<NamedObject> objects = new ArrayList<>();
//        List<History> histories = new ArrayList<>();
//
//        for(Question question: questions){
//            if(question.getSelectAnswer().getStatus()){
//                try{
//                    user.incrementScore(1);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            History history = makeHistory(user,question,sessionId,HistoryType.Exam);
//            histories.add(history);
//        }
//
//        objects.add(new NamedObject(user,"u"));
//        objects.add(new NamedObject(histories, "h"));
//
//        return objects;
//    }
//    private History makeHistory(User user, Question question, int sessionId, HistoryType type){
//        History history = new History();
//        history.setUser(user);
//        history.setQuestion(question);
//        history.setMyAnswer(question.getSelectAnswer());
//        history.setSessionId(sessionId);
//        history.setHistoryType(type);
//        history.setDate(new Date().getTime());
//        return history;
//    }
//    public void savePrepToHistory(Prep prep, User user) {
//        List<History> histories = new ArrayList<>();
//        int sessionId = getSessionId();
//
//        for(Question q: prep.getQuestion()){
//            History history = makeHistory(user, q,sessionId,HistoryType.Prep);
//            history.setDate(new Date().getTime());
//            histories.add(history);
//        }
//
//        historyRepo.save(histories);
//    }


    private Integer getSessionId(){
        Integer sessionId = historyRepo.getHistoryByMaxSessionId();

        if(sessionId == null){
            sessionId = 1;
        }else{
            sessionId++;
        }

        return sessionId;
    }

//    public List<ExamHistory> getListOfExamHistoriesByUser(String userId){
//        List<History> histories = historyRepo.findHistoriesByHistoryTypeAndUser_UserId(HistoryType.Exam,userId);
//        HashMap<Integer,ExamHistory> examHistoryHashMap = new HashMap<>();
//
//        HashMap<Integer,List<History>> historyBySessionId = new HashMap<>();
//
//        List<History> historyListBySessionId = new ArrayList<>();
//
//        for(History history: historyListBySessionId){
//
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        for(History history: histories){
//            if(examHistoryHashMap.get(history.getSessionId()) != null){
//
//                Subject subjectTop = examHistoryHashMap.get(history.getSessionId()).getSubjectTop();
//                Subject subjectMid = examHistoryHashMap.get(history.getSessionId()).getSubjectMid();
//                Subject subjectBot = examHistoryHashMap.get(history.getSessionId()).getSubjectBot();
//
//                Subject historySubject = history.getSubject();
//
//                if(historySubject.getSubjectId().equals(subjectTop.getSubjectId())
//                        || historySubject.getSubjectId().equals(subjectMid.getSubjectId())
//                        || historySubject.getSubjectId().equals(subjectBot.getSubjectId())){
//
//                    if(subjectMid == null){
//
//                    }else if(subjectBot == null){
//
//                    }
//                }
//
//                if(examHistoryHashMap.get(history.getSessionId()).getSubjectTop() != null){
//                    if(examHistoryHashMap.get(history.getSessionId()).getSubjectTop().getSubjectId().equals(history.getSubject().getSubjectId())){
//                        examHistoryHashMap.get(history.getSessionId()).setResultTop(examHistoryHashMap.get(history.getSessionId()).getResultTop()+1);
//                    }
//                }
//
//                if(examHistoryHashMap.get(history.getSessionId()).getSubjectMid().getSubjectId().equals(history.getSubject().getSubjectId()))
//                    examHistoryHashMap.get(history.getSessionId()).setResultMid(examHistoryHashMap.get(history.getSessionId()).getResultMid()+1);
//
//                if(examHistoryHashMap.get(history.getSessionId()).getSubjectBot().getSubjectId().equals(history.getSubject().getSubjectId()))
//                    examHistoryHashMap.get(history.getSessionId()).setResultBot(examHistoryHashMap.get(history.getSessionId()).getResultBot()+1);
//
//            }else{
//                ExamHistory examHistory = new ExamHistory();
//                examHistory.setSubjectTop(history.getSubject());
//                examHistory.setResultTop(0);
//                examHistory.setDate(new Date().getTime());
//                examHistory.setSessionId(history.getSessionId());
//
//                if(history.getMyAnswer().getStatus()){
//                    examHistory.setResultTop(1);
//                }
//
//                examHistoryHashMap.put(history.getSessionId(),examHistory);
//            }
//        }
//
//        return new ArrayList<>(examHistoryHashMap.values());
//    }

    public List<ExamHistory> getListOfExamHistoriesByUser(User user){

        List<History> allHistory = historyRepo.findHistoriesByHistoryTypeAndUser_UserId(HistoryType.Exam,user.getUserId());
        HashMap<Integer,List<History>> historyListsBySessionId = new HashMap<>();

        allHistory.forEach(history -> {
            if(historyListsBySessionId.get(history.getSessionId()) == null){
                historyListsBySessionId.put(history.getSessionId(),new ArrayList<>());
                historyListsBySessionId.get(history.getSessionId()).add(history);
            }else{
                historyListsBySessionId.get(history.getSessionId()).add(history);
            }
        });

        List<ExamHistory> examHistories = new ArrayList<>();

        historyListsBySessionId.forEach((sessionId, historyList) -> {
            examHistories.add(makeExamHistoryFromListOfHistories(historyList,sessionId));
        });

        return examHistories;
    }

    private ExamHistory makeExamHistoryFromListOfHistories(List<History> histories, Integer examId){

        HashMap<Subject,List<History>> subjectHistory = new HashMap<>();

        histories.forEach(history -> {
            if(subjectHistory.get(history.getSubject()) != null){
                subjectHistory.get(history.getSubject()).add(history);
            }else{
                subjectHistory.put(history.getSubject(),new ArrayList<>());
                subjectHistory.get(history.getSubject()).add(history);
            }
        });

        List<Subject> subjects = new ArrayList<>(subjectHistory.keySet());

        Subject subjectTop = subjects.get(0);
        subjectTop.setTopics(null);
        Subject subjectMid = subjects.get(1);
        subjectMid.setTopics(null);
        Subject subjectBot = subjects.get(2);
        subjectBot.setTopics(null);

        ExamHistory examHistory = new ExamHistory();
        examHistory.setExamId(examId);
        examHistory.setSubjectTop(subjectTop);
        examHistory.setSubjectMid(subjectMid);
        examHistory.setSubjectBot(subjectBot);
        examHistory.initializeResults();

        subjectHistory.forEach((subject, historyList) -> {
            historyList.forEach(history -> {
                examHistory.setDate(history.getDate());
                if(history.getMyAnswer().getStatus()){
                    examHistory.incrementResult(subject);
                }
            });
        });

        return examHistory;
    }

    public List<PrepHistory> getPrepHistoryByUserId(String userId){
        List<History> histories = historyRepo.findHistoriesByHistoryTypeAndUser_UserId(HistoryType.Prep,userId);
        HashMap<Integer,PrepHistory> prepHistoryHashMap = new HashMap<>();

        for(History history: histories){
            if(prepHistoryHashMap.get(history.getSessionId()) != null){
                if(history.getMyAnswer().getStatus()){
                    prepHistoryHashMap.get(history.getSessionId()).setResult(prepHistoryHashMap.get(history.getSessionId()).getResult() + 1);
                }
            }else{
                PrepHistory prepHistory = new PrepHistory();
                Topic topic = history.getQuestion().getTopic();
                Subject subject = topic.getSubject();
                subject.setTopics(null);
                prepHistory.setDate(prepHistory.getDate());
                prepHistory.setSubject(subject);
                prepHistory.setResult(0);
                prepHistory.setId(history.getSessionId());
                prepHistory.setDate(history.getDate());

                if(history.getMyAnswer().getStatus()){
                    prepHistory.setResult(prepHistory.getResult() + 1);
                }

                prepHistoryHashMap.put(history.getSessionId(),prepHistory);
            }
        }

        return new ArrayList<>(prepHistoryHashMap.values());
    }

}

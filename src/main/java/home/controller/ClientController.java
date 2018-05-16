package home.controller;

import home.entity.*;
import home.exception.NotEnoughQuestionsException;
import home.exception.NotEnoughTopicException;
import home.exception.NotEnoughTopicsWithSufficentQuestions;
import home.exception.QuestionConfigurationException;
import home.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static home.constant.Constants.*;

@RestController
@RequestMapping("/api")
public class ClientController extends BaseController{

    @PostMapping("/prepresult")
    @ResponseBody Object postPrepResult(@RequestBody Prep prep){
        historyService.saveToPrep(user(),prep);
        return ok("Saved to history");
    }

    @GetMapping("/prephistory")
    @ResponseBody Object prepByUserId(){
        return historyService.getPrepHistoryByUserId(user().getUserId());
    }

    @PostMapping("/examresult")
    @ResponseBody Object postExamResult(@RequestBody Exam exam){
        historyService.saveExamHistory(user(),exam);
        Object result = getMyUni(exam,calculateCorrectAnswers(exam,user().getUserId()));
        return ok(result);
    }

    @PostMapping("/uniresult")
    @ResponseBody Object postExamHistoryToGetUnis(@RequestBody ExamHistory examHistory){
        Object result = getMyUni(examHistory);
        return ok(result);
    }

    @GetMapping("/examhistory")
    @ResponseBody Object historyByUserId(){
        return historyService.getListOfExamHistoriesByUser(user());
    }

    @GetMapping(value = "/{lang}/exam/{subjectTop}/{subjectMid}/{subjectBot}")
    @ResponseBody Object exam(@PathVariable int lang, @PathVariable int subjectTop, @PathVariable int subjectMid , @PathVariable int subjectBot){
        try{
            return ok(examEngine.makeExam(lang,subjectTop,subjectMid,subjectBot));
        }catch (QuestionConfigurationException e){
            e.printStackTrace();
            return badRequest(e.getMessage());
        }catch (NotEnoughQuestionsException e){
            e.printStackTrace();
            return badRequest(e.getMessage());
        }catch (NotEnoughTopicException e){
            e.printStackTrace();
            return badRequest(e.getMessage());
        }catch (NotEnoughTopicsWithSufficentQuestions e){
            e.printStackTrace();
            return badRequest(e.getMessage());
        }
    }


    @GetMapping("/{lang}/prep/{topicId}/{numOfQuestionAsked}")
    @ResponseBody Object prep(@PathVariable int lang, @PathVariable int topicId, @PathVariable int numOfQuestionAsked){
        try{
            Prep prep = new Prep();

            Topic topic = topicRepo.findOne(topicId);
            Subject subject = subjectRepo.findOne(topic.getSubject().getSubjectId());
            List<Question> questions = questionService.getQuestionsByTopic(lang, topicId, numOfQuestionAsked);

            prep.setSubject(subject);
            prep.setTopic(topic);
            prep.setQuestion(questions);

            return ok(prep);
        }catch (NotEnoughQuestionsException e){
            e.getMessage();
            return badRequest(e.getMessage());
        }

    }

    @PutMapping("/lastseen")
    @ResponseBody Object lastseen(){
        User user = userRepo.findOne(user().getUserId());
        user.setLastSeen(new Date());
        userRepo.save(user);
        return ok("success");
    }

    @GetMapping("/leaderboard")
    @ResponseBody Object leaderboard() {
        return getLederboard();
    }

    @GetMapping("/gettopics/{subjectId}")
    @ResponseBody Object getTopics(@PathVariable int subjectId) {
        return topicService.findTopicsBySubjectId(subjectId);
    }

    @GetMapping("/getsubjects")
    @ResponseBody Object getSubject() {
        return subjectRepo.findAll();
    }

    @GetMapping("/qa/question")
    @ResponseBody Object getQuestionQA() {
        return ok(questionQARepo.findAll());
    }

    @PostMapping("/qa/question")
    @ResponseBody Object postQuestionQA(@RequestBody QuestionQA questionQA) {
        AnswerQA answerQA = answerQARepo.save(questionQA.getAnswer());
        questionQA.setAnswer(answerQA);
        questionQARepo.save(questionQA);
        return ok("message","QA saved");
    }

    @GetMapping("/course")
    @ResponseBody Object getCourse() {
        return ok(courseRepo.findAll());
    }

    @GetMapping("/faq")
    @ResponseBody Object getFaq() {
        return ok(questionQARepo.findAll());
    }

    @GetMapping("/course/{courseId}")
    @ResponseBody Object getCourse(@PathVariable int courseId) {
        return ok(courseRepo.findOne(courseId));
    }

    @PostMapping("/course")
    @ResponseBody Object postCourse(@RequestBody Course course) {
        return ok(courseRepo.save(course));
    }

    @GetMapping("/update")
    @ResponseBody Object update(){
        HashMap<String, Boolean> isUpdating = new HashMap<>();
        isUpdating.put("isUpdating",IS_MOBILE_APP_IN_UPDATE_MODE);
        return ok(isUpdating);
    }

    @GetMapping("/updating")
    @ResponseBody Object updating(){
        IS_MOBILE_APP_IN_UPDATE_MODE = true;
        return ok("ok");
    }

    @GetMapping("/updated")
    @ResponseBody Object updated(){
        IS_MOBILE_APP_IN_UPDATE_MODE = false;
        return ok("ok");
    }

    @GetMapping("/university/{regionId}")
    @ResponseBody Object getUniversities(@PathVariable int regionId){
        return universityRepo.findAllByRegion(getRegion(regionId));
    }


    @GetMapping("/regions")
    @ResponseBody Object regions(){

        HashMap<Integer,String> regions = new HashMap<>();
        regions.put(ANDIJON_ID,ANDIJON_STRING);
        regions.put(BUXORO_ID,BUXORO_STRING);
        regions.put(FARGONA_ID,FARGONA_STRING);
        regions.put(JIZZAX_ID,JIZZAX_STRING);
        regions.put(QASHQADARYO_ID,QASHQADARYO_STRING);
        regions.put(XORAZM_ID,XORAZM_STRING);
        regions.put(NAVOIY_ID,NAVOIY_STRING);
        regions.put(NAMANGAN_ID,NAMANGAN_STRING);
        regions.put(SAMARQAND_ID,SAMARQAND_STRING);
        regions.put(SURXANDARYO_ID,SURXANDARYO_STRING);
        regions.put(SIRDARYO_ID,SIRDARYO_STRING);
        regions.put(TASHKENT_ID,TASHKENT_STRING);
        regions.put(TASHKENT_CITY_ID,TASHKENT_CITY_STRING);
        return regions;
    }

    String getRegion(int universityId){

        HashMap<Integer,String> regions = new HashMap<>();
        regions.put(ANDIJON_ID,ANDIJON_STRING);
        regions.put(BUXORO_ID,BUXORO_STRING);
        regions.put(FARGONA_ID,FARGONA_STRING);
        regions.put(JIZZAX_ID,JIZZAX_STRING);
        regions.put(QASHQADARYO_ID,QASHQADARYO_STRING);
        regions.put(XORAZM_ID,XORAZM_STRING);
        regions.put(NAVOIY_ID,NAVOIY_STRING);
        regions.put(NAMANGAN_ID,NAMANGAN_STRING);
        regions.put(SAMARQAND_ID,SAMARQAND_STRING);
        regions.put(SURXANDARYO_ID,SURXANDARYO_STRING);
        regions.put(SIRDARYO_ID,SIRDARYO_STRING);
        regions.put(TASHKENT_ID,TASHKENT_STRING);
        regions.put(TASHKENT_CITY_ID,TASHKENT_CITY_STRING);
        return regions.get(universityId);
    }

    //    endregion

}

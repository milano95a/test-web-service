package home.controller.view;

import home.controller.BaseController;
import home.entity.Answer;
import home.entity.Faculty;
import home.entity.History;
import home.entity.Question;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDeleteController extends BaseController {

    @GetMapping("/question/{pageNumber}/delete/{id}")
    RedirectView deleteQuestion(Model model, @PathVariable int pageNumber, @PathVariable int id){
        try{
            List<History> histories = historyRepo.findAllByQuestion_QuestionId(id);
            histories.forEach(h -> historyRepo.delete(h.getHistoryId()));
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            List<Answer> answers = answerRepo.findAllByQuestion_QuestionId(id);
            answers.forEach(a -> answerRepo.delete(a.getAnswerId()));
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            questionRepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new RedirectView("/admin/questions/" + pageNumber);
    }

    @GetMapping("/course/{pageNumber}/delete/{id}")
    RedirectView deleteCourse(Model model, @PathVariable int pageNumber, @PathVariable int id){
        try{
            courseRepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RedirectView("/admin/courses/"+pageNumber);
    }

    @GetMapping("/faculty/{pageNumber}/delete/{id}")
    RedirectView deleteFaculty(Model model, @PathVariable int pageNumber, @PathVariable int id){
        try{
            facultyRepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RedirectView("/admin/faculties/"+pageNumber);
    }

    @GetMapping("/faq/{pageNumber}/delete/{id}")
    RedirectView deleteQA(Model model, @PathVariable int pageNumber, @PathVariable int id){
        try{
            questionQARepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RedirectView("/admin/faqs/"+pageNumber);
    }

    @GetMapping("/topic/{pageNumber}/delete/{id}")
    RedirectView deleteTopic(Model model, @PathVariable int pageNumber, @PathVariable int id){
        try{
            topicRepo.delete(id);
        }catch (DataIntegrityViolationException e) {
            System.out.println("You cannot delete topics that contain questions");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new RedirectView("/admin/topics/"+pageNumber);
    }

    @GetMapping("/university/{pageNumber}/delete/{id}")
    RedirectView deleteUniversity(Model model, @PathVariable int pageNumber, @PathVariable int id){
        try{
            List<Faculty> faculties = facultyRepo.findAllByUniversityUniversityId(id);
            facultyRepo.delete(faculties);
            universityRepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RedirectView("/admin/universities/"+pageNumber);
    }
}
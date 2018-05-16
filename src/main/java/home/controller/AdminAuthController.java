package home.controller;

import home.constant.Links;
import home.constant.UzLang;
import home.security.domain.Admin;
import home.utils.SHA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by AB on 06-Sep-17.
 */

@Controller
@RequestMapping("/admin")
public class AdminAuthController extends BaseController{

    private static final String LOGIN_SUBMIT = "/admin/login";

    @GetMapping("/login")
    String getLogin(Model model){

        Links links = new Links();
        links.setForm_submit(LOGIN_SUBMIT);
        UzLang uzLang = new UzLang();
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("object", new Admin());

        return "login";
    }

    @GetMapping("/err")
    String error(){
        throw new IndexOutOfBoundsException("I throw this error");
    }

    @GetMapping("/logout")
    RedirectView logout(){
        return new RedirectView("/admin/login");
    }

    @GetMapping("/login/{message}")
    String returnLogin(@PathVariable String message, Model model){

        Links links = new Links();
        links.setForm_submit(LOGIN_SUBMIT);
        UzLang uzLang = new UzLang();

        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("object", new Admin());

        if(message.equals("success")){
            model.addAttribute("success", "New password sent to your email");
        }else if(message.equals("error1")){
            model.addAttribute("error", "Email not found");
        }else if(message.equals("error2")){
            model.addAttribute("error", "Wrong password");
        }else if(message.equals("error3")){
            model.addAttribute("signUpError", "Email not found");
        }else{
            model.addAttribute("error", "Email not found");
        }

        return "login";
    }

    @PostMapping("/login")
    RedirectView postLogin(@ModelAttribute Admin admin){

        if(admin.getUsername()!= null){
            Admin dbAdmin = adminRepo.findAdminByUsername(admin.getUsername());

            if(dbAdmin != null){
                if(dbAdmin.getPassword().equals(SHA.hash(admin.getPassword()))){
                    return new RedirectView("/admin/questions/1");
                }else{
                    return new RedirectView("/admin/login/error2");
                }
            }
        }

        return new RedirectView("/admin/login/error1");
    }

    @PostMapping("/forgot")
    RedirectView forgot(@ModelAttribute Admin admin){
        if(adminRepo.findAdminByUsername(admin.getUsername()) != null){
            Admin dbAdmin = adminRepo.findAdminByUsername(admin.getUsername());
            String newPass = "12345";
            dbAdmin.setPassword(SHA.hash(newPass));
            emailUtil.sendPassword(dbAdmin.getUsername(),newPass);

            return new RedirectView("/admin/login/success/#signup");
        }

        return new RedirectView("/admin/login/error3/#signup");
    }
}

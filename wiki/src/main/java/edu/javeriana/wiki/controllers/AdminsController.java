package edu.javeriana.wiki.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminsController {
    public record TeamMember(String name, String role, String languages, String profile, String imageUrl){}
    @GetMapping("/presentation")
    public String printPresentation(Model model) {
        List<TeamMember> teamMembers = new ArrayList<>();
        teamMembers.add(new TeamMember("Cristian", "CSO", "Java, Python", "Perfil: ","/images/Cristian.png"));
        teamMembers.add(new TeamMember("Angel", "CTO", "Java, Python", "Perfil:","imageCristian"));
        teamMembers.add(new TeamMember("Daniel", "Arquitecto de Software", "C++, Kotlin, Java, Python", "Perfil: Me gusta hacer diagramas de clases (Soy un psicopata)","imageCristian"));
        teamMembers.add(new TeamMember("Nicolas", "CIO", "C++, Kotlin, Java, Python, HTML, CSS, JavaScript", "Perfil: Me gusta el manejo y procesamiento de datos por razones aun desconocidas","/images/nicolas.jpg"));
        teamMembers.add(new TeamMember("Miguel", "CPO", "Java, Python", "Perfil:","imageCristian"));
        model.addAttribute("teamMembers", teamMembers);
        return "Presentation";
    }
}
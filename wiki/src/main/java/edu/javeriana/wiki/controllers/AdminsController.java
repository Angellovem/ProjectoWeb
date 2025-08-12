package edu.javeriana.wiki.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminsController {
    public record TeamMember(String name, String role, String languages, String profile){}
    @GetMapping("/presentation")
    public String printPresentation(Model model) {
        List<TeamMember> teamMembers = new ArrayList<>();
        teamMembers.add(new TeamMember("Cristian", "CSO", "Java, Python", "Perfil: "));
        teamMembers.add(new TeamMember("Angel", "CTO", "Java, Python", "Perfil:"));
        teamMembers.add(new TeamMember("Daniel", "Arquitecto de Software", "C++, Kotlin, Java, Python", "Perfil: Me gusta hacer diagramas de clases (Soy un psicopata)"));
        teamMembers.add(new TeamMember("Nicolas", "CIO", "Java, Python", "Perfil:"));
        teamMembers.add(new TeamMember("Miguel", "CPO", "Java, Python", "Perfil:"));
        model.addAttribute("teamMembers", teamMembers);
        return "Presentation";
    }
}
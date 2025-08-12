package edu.javeriana.wiki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.javeriana.wiki.model.Formulario;
import edu.javeriana.wiki.repository.FormularioRepository;
import jakarta.validation.Valid;

@Controller
public class ContactController {

    private final FormularioRepository repo;

    public ContactController(FormularioRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/contacto")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("formulario")) {
            model.addAttribute("formulario", new Formulario());
        }
        return "Contact"; 
    }

    @PostMapping("/contacto")
    public String enviarFormulario(
            @Valid @ModelAttribute("formulario") Formulario form,
            BindingResult br,
            RedirectAttributes ra) {

        if (br.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.formulario", br);
            ra.addFlashAttribute("formulario", form);
            return "redirect:/contacto";
        }

        repo.save(form);
        ra.addFlashAttribute("success", "¡Tu mensaje fue enviado correctamente!");
        return "redirect:/contacto";
    }
}

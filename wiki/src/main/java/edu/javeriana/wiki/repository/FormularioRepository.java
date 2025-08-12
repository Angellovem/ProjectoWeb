package edu.javeriana.wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.javeriana.wiki.model.Formulario;

public interface FormularioRepository extends JpaRepository<Formulario, Long> {
}

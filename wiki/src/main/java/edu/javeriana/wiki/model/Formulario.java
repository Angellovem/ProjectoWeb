package edu.javeriana.wiki.model;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact_form")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombres es obligatorio")
    @Size(max = 100, message = "Nombres no puede exceder 100 caracteres")
    private String nombres;

    @NotBlank(message = "Apellidos es obligatorio")
    @Size(max = 100, message = "Apellidos no puede exceder 100 caracteres")
    private String apellidos;

    @NotBlank(message = "Correo es obligatorio")
    @Size(max = 100, message = "Correo no puede exceder 100 caracteres")
    @Pattern(
        regexp = "^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
        message = "Correo inválido: use MAYÚSCULAS, sin espacios, con '@' y un punto después"
    )
    private String correo;

    @NotNull(message = "Semestre es obligatorio")
    @Min(value = 0, message = "Semestre mínimo 0")
    @Max(value = 16, message = "Semestre máximo 16")
    private Integer semestre;

    @NotBlank(message = "Descripción es obligatoria")
    @Size(max = 500, message = "Descripción no puede exceder 500 caracteres")
    @Column(length = 500)
    private String descripcion;

    private LocalDateTime createdAt;

    @PrePersist
    @PreUpdate
    private void preSave() {
        // Correo en MAYÚSCULAS y sin acentos/espacios
        if (correo != null) {
            correo = toAsciiUpper(correo).replaceAll("\\s+", "");
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    private static String toAsciiUpper(String s) {
        String n = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        n = n.replace('ñ', 'N').replace('Ñ', 'N');
        return n.toUpperCase(Locale.ROOT);
    }

    public Long getId() { return id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}


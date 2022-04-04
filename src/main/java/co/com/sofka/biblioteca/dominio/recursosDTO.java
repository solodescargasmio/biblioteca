package co.com.sofka.biblioteca.dominio;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(value = "recurso")
public class recursosDTO {
    @Id
    private String id= UUID.randomUUID().toString().substring(0,10);

    private String prestado;
    private LocalDate fechaPrestamo;
    private String titulo;
    private String descripcion;
    private String tipoRecurso;
    private String tematica;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getPrestado() {
        return prestado;
    }

    public void setPrestado(String prestado) {
        this.prestado = prestado;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

}

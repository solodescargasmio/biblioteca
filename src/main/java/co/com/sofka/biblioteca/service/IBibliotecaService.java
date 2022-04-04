package co.com.sofka.biblioteca.service;

import co.com.sofka.biblioteca.dominio.recursosDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IBibliotecaService {

    Mono<recursosDTO> save(recursosDTO rDTO);
    Flux<recursosDTO> findAll();
    Mono<recursosDTO> findById(String id);
    Mono<String> prestarRecurso(String id);
    Mono<String> findDisponible(String id);
    Mono<String> devolverRecurso(String id);
    Mono<recursosDTO> update(String id,recursosDTO rDTO);


}

package co.com.sofka.biblioteca.repository;

import co.com.sofka.biblioteca.dominio.recursosDTO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface bibliotecaRepository extends ReactiveMongoRepository<recursosDTO, String> {

        Mono<recursosDTO> findById(String Id);

        @Query("{'tematica':?0}")
        Flux<recursosDTO> findByTematica(String id);

        @Query("{'tipoRecurso':?0}")
        Flux<recursosDTO> findByTipo(String id);




}

package co.com.sofka.biblioteca.web;

import co.com.sofka.biblioteca.dominio.recursosDTO;
import co.com.sofka.biblioteca.service.IBibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


@RestController
public class recursoResource {

    @Autowired
    private IBibliotecaService iBibliotecaService;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/recursos")
    private Flux<recursosDTO> findAll(){
        System.out.println("Dentro de recursos");
        return this.iBibliotecaService.findAll();
    }

    @GetMapping("/recurso/{id}")
    private Mono<recursosDTO> findById(@PathVariable("id") String id){
        return this.iBibliotecaService.findById(id);
    }
    @GetMapping("/recurso/disponible/{id}")
    private Mono<String> findDisponible(@PathVariable("id") String id){
        return this.iBibliotecaService.findDisponible(id);
    }

    @PostMapping("/")
    private Mono<recursosDTO> save(@RequestBody recursosDTO rDTO){
        return this.iBibliotecaService.save(rDTO);
    }


    @PostMapping("/recurso/prestamo/{id}")
    private Mono<String> prestarRecurso(@PathVariable("id") String id){
        return  this.iBibliotecaService.prestarRecurso(id);
    }
    @PostMapping("/recurso/devolucion/{id}")
    private Mono<String> devolverRecurso(@PathVariable("id") String id){
        return  this.iBibliotecaService.devolverRecurso(id);
    }

    @PutMapping("/recurso")
    private Mono<recursosDTO> modificarRecurso(@RequestBody recursosDTO rDTO){
        return  this.iBibliotecaService.update(rDTO);
    }

}


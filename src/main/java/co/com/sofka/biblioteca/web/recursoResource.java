package co.com.sofka.biblioteca.web;

import co.com.sofka.biblioteca.dominio.recursosDTO;
import co.com.sofka.biblioteca.service.IBibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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

    @PutMapping("/recurso/{id}")
    private Mono<ResponseEntity<recursosDTO>> update(@PathVariable("id") String id, @RequestBody recursosDTO rDTO){
        return this.iBibliotecaService.update(id, rDTO)
                .flatMap(rDTO1 -> Mono.just(ResponseEntity.ok(rDTO1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/recurso/{id}")
    private  Mono<ResponseEntity<recursosDTO>> delete(@PathVariable("id") String id){
        return this.iBibliotecaService.delete(id)
                .flatMap(rDTO -> Mono.just(ResponseEntity.ok(rDTO)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
/*return this.messageService.delete(id)
                .flatMap(message -> Mono.just(ResponseEntity.ok(message)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
}


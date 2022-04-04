package co.com.sofka.biblioteca.service.impl;

import co.com.sofka.biblioteca.dominio.recursosDTO;
import co.com.sofka.biblioteca.repository.bibliotecaRepository;
import co.com.sofka.biblioteca.service.IBibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;


@Service
public class bibliotecaServiceImpl implements IBibliotecaService {
    @Autowired
    private bibliotecaRepository bibRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Mono<recursosDTO> save(recursosDTO rDTO) {
        return this.bibRepository.save(rDTO);
    }

    @Override
    public Flux<recursosDTO> findAll() {
        return this.bibRepository.findAll();
    }

    @Override
    public Mono<recursosDTO> findById(String id) {
        return bibRepository.findById(id);
    }

    @Override
    public Mono<String> prestarRecurso(String id) {
        String message="";
        LocalDate fecha=LocalDate.now();
        Query query=new Query(Criteria.where("_id").is(id));
        recursosDTO rDTO=mongoTemplate.findOne(query,recursosDTO.class);
        if(rDTO.getPrestado().equals("SI")){
            message = "Este recuso no está disponible. Prestado el "+rDTO.getFechaPrestamo();
        }else{
            rDTO.setPrestado("SI");
            rDTO.setFechaPrestamo(fecha);
            message="Recurso asignado a usted "+fecha;
          try {
              mongoTemplate.save(rDTO);
          }catch (Exception ex){
              System.out.println("Error save "+ex.getMessage());
              message=message+"  "+ex.getMessage();
          }
        }
        return Mono.just(message);


    }

    @Override
    public Mono<String> findDisponible(String id) {
        String message="";
        Query query=new Query(Criteria.where("id").is(id));
        recursosDTO rDTO=mongoTemplate.findOne(query,recursosDTO.class);
        if(rDTO.getPrestado().equals("SI")){
            message = "Este recuso no está disponible. En prestado desde "+rDTO.getFechaPrestamo();
        }else{
            message = "Recuso disponible";
        }
        return Mono.just(message);
    }

    @Override
    public Mono<String> devolverRecurso(String id) {
        String message="";
        LocalDate fecha=LocalDate.now();
        Query query=new Query(Criteria.where("_id").is(id));
        recursosDTO rDTO=mongoTemplate.findOne(query,recursosDTO.class);
        if(rDTO.getPrestado().equals("NO")){
            message = "Recurso en stock, no se encuentra en prestamo";
        }else{
            rDTO.setPrestado("NO");
            rDTO.setFechaPrestamo(fecha);
            message="Devolucion correcta : recurso devuelto "+rDTO.getTipoRecurso();
            try {
                mongoTemplate.save(rDTO);
            }catch (Exception ex){
                System.out.println("Error save "+ex.getMessage());
                message=message+"  "+ex.getMessage();
            }
        }
        return Mono.just(message);


    }

    @Override
    public Mono<recursosDTO> update(recursosDTO rDTO) {
        return null;//this.bibRepository.findById(rDTO.getId()).flatMap(recursosDTO -> recursosDTO.);
    }

}

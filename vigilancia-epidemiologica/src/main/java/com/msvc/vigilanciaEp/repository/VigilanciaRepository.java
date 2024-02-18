package com.msvc.vigilanciaEp.repository;


import com.msvc.vigilanciaEp.model.Vigilancia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VigilanciaRepository extends MongoRepository<Vigilancia,String > {
}

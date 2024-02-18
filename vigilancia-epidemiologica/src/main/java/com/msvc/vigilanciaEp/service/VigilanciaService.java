package com.msvc.vigilanciaEp.service;

import com.msvc.vigilanciaEp.dto.VigilanciaRequest;
import com.msvc.vigilanciaEp.dto.VigilanciaResponse;
import com.msvc.vigilanciaEp.model.Vigilancia;
import com.msvc.vigilanciaEp.repository.VigilanciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j /*LOG*/
public class VigilanciaService {

    /*INYECTAMOS LOS REPOSITORIOS*/
    @Autowired
    private VigilanciaRepository vigilanciaRepository;

    /*metodo para mapear*/

    /*Utiliza la construcción de VigilanciaResponse mediante el uso del patrón Builder y llama a
    métodos getter
    de la entidad Vigilancia para obtener sus propiedades.*/
    private VigilanciaResponse mapToVigilanciaResponse(Vigilancia vigilancia){
        return  VigilanciaResponse.builder()
                ._id(vigilancia.get_id())
                .hospital(vigilancia.getHospital())
                .ubicacion(vigilancia.getUbicacion())
                .totalCasos(vigilancia.getTotalCasos())
                .virusCasos(mapVirusCasosToResponse(vigilancia.getVirusCasos()))
                .bacteriasCasos(mapBacteriasCasosToResponse(vigilancia.getBacteriasCasos()))
                .build();
    }

    /*metdo para obtener todos los hospitales*/

    /*Este método utiliza el repositorio para obtener todas las entidades Vigilancia almacenadas y luego utiliza
    el método mapToVigilanciaResponse para convertirlas en objetos VigilanciaResponse.
    Utiliza Collectors.toList() para recopilar los resultados en una lista.*/
    public List<VigilanciaResponse> getAllHospitales(){
        List<Vigilancia> vigilancias =vigilanciaRepository.findAll();
        return vigilancias.stream().map(this::mapToVigilanciaResponse).collect(Collectors.toList());

    }

    /*Estos métodos toman listas de VirusCasos y BacteriasCasos respectivamente de la entidad/modelo Vigilancia y
    las mapean a listas correspondientes en VigilanciaResponse.
    Nuevamente, se utiliza el patrón Builder para construir objetos VirusCasos y BacteriasCasos.*/

    private List<VigilanciaResponse.VirusCasos> mapVirusCasosToResponse(List<Vigilancia.VirusCasos> virusCasos) {
        return virusCasos.stream()
                .map(vc -> VigilanciaResponse.VirusCasos.builder()
                        .nombreVirus(vc.getNombreVirus())
                        .casosVirus(vc.getCasosVirus())
                        .build())
                .collect(Collectors.toList());
    }

    private List<VigilanciaResponse.BacteriasCasos> mapBacteriasCasosToResponse(List<Vigilancia.BacteriasCasos> bacteriasCasos){
        return bacteriasCasos.stream()
                .map(bc -> VigilanciaResponse.BacteriasCasos.builder()
                        .nombreBacteria(bc.getNombreBacteria())
                        .casosBacteria(bc.getCasosBacteria())
                        .build())
                .collect(Collectors.toList());
    }

    public void createHospital(VigilanciaRequest vigilanciaRequest) {
        Vigilancia vigilancia = Vigilancia.builder()
                .hospital(vigilanciaRequest.getHospital())
                .ubicacion(vigilanciaRequest.getUbicacion())
                .totalCasos(vigilanciaRequest.getTotalCasos())
                .virusCasos(mapVirusCasos(vigilanciaRequest.getVirusCasos()))
                .bacteriasCasos(mapBacteriasCasos(vigilanciaRequest.getBacteriasCasos()))
                .build();

        vigilanciaRepository.save(vigilancia);
    }

    /*para request*/
    private List<Vigilancia.VirusCasos> mapVirusCasos(List<VigilanciaRequest.VirusCasos> virusCasos) {
        return virusCasos.stream()
                .map(vc -> Vigilancia.VirusCasos.builder()
                        .nombreVirus(vc.getNombreVirus())
                        .casosVirus(vc.getCasosVirus())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Vigilancia.BacteriasCasos> mapBacteriasCasos(List<VigilanciaRequest.BacteriasCasos> bacteriasCasos) {
        return bacteriasCasos.stream()
                .map(bc -> Vigilancia.BacteriasCasos.builder()
                        .nombreBacteria(bc.getNombreBacteria())
                        .casosBacteria(bc.getCasosBacteria())
                        .build())
                .collect(Collectors.toList());
    }

}

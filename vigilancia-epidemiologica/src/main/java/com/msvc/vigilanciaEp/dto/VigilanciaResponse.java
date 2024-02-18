package com.msvc.vigilanciaEp.dto;

import com.msvc.vigilanciaEp.model.Vigilancia;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "HospitalesCDMX")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VigilanciaResponse {
    private String _id;
    private String hospital;

    private String ubicacion;
    private int totalCasos;

    private List<VirusCasos> virusCasos;

    private List<BacteriasCasos> bacteriasCasos;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    @Getter
    @Setter
    public static class VirusCasos {
        private String nombreVirus;
        private int casosVirus;


    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BacteriasCasos{
        private String nombreBacteria;
        private int casosBacteria;

    }


}

package com.msvc.vigilanciaEp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "HospitalesCDMX")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Vigilancia {

    @Id
    private String _id;
    private String hospital;

    private String ubicacion;

    private int totalCasos;

    private List<VirusCasos> virusCasos;

    private List<BacteriasCasos> bacteriasCasos;

    @Getter
    @Setter
    @Builder
    public static class VirusCasos {
        private String nombreVirus;
        private int casosVirus;


    }

    @Getter
    @Setter
    @Builder
    public static class BacteriasCasos{
        private String nombreBacteria;
        private int casosBacteria;

    }


}

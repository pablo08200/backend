package com.msvc.vigilanciaEp.controller;

import com.msvc.vigilanciaEp.dto.VigilanciaRequest;
import com.msvc.vigilanciaEp.dto.VigilanciaResponse;
import com.msvc.vigilanciaEp.model.Vigilancia;
import com.msvc.vigilanciaEp.service.VigilanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1/vigilancia")
public class VigilanciaController {

    @Autowired
    private VigilanciaService vigilanciaService;

    @GetMapping("/obtenerHospitales")
    @ResponseStatus(HttpStatus.OK)
    public List<VigilanciaResponse> listarHospitales(){
        return vigilanciaService.getAllHospitales();
    }



    @PostMapping("/saveHospital")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHospital(@RequestBody VigilanciaRequest vigilanciaRequest){

        vigilanciaService.createHospital(vigilanciaRequest);
    }
}

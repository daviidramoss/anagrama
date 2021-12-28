package com.freeagent.anagram.controller;

import com.freeagent.anagram.dto.request.PalabraDTO;
import com.freeagent.anagram.model.Palabra;
import com.freeagent.anagram.service.DiccionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("diccionario")
public class ApiController {

    private DiccionarioService diccionarioService;

    @Autowired
    public ApiController(DiccionarioService diccionarioService) {
        this.diccionarioService = diccionarioService;
    }


    @PostMapping("/anagrama")
    private ResponseEntity<?> anagram(@Valid @RequestBody PalabraDTO palabraRequest) {
        List<PalabraDTO> anagramas;
        try {
            Optional<Palabra> palabra = diccionarioService.obtenerPalabra(palabraRequest);
            if (!palabra.isPresent()) {
                return new ResponseEntity<>("Esta palabra no se encuentra en el diccionario", HttpStatus.NOT_FOUND);
            }

            anagramas = diccionarioService.obtenerAnagramas(palabra.get());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(anagramas, HttpStatus.OK);
    }

    @PostMapping("/anagrama/db")
    private ResponseEntity<?> anagramDataBase(@Valid @RequestBody PalabraDTO palabraRequest) {
        List<PalabraDTO> anagramas;
        try {
            Optional<Palabra> palabra = diccionarioService.obtenerPalabra(palabraRequest);
            if (!palabra.isPresent()) {
                return new ResponseEntity<>("Esta palabra no se encuentra en el diccionario", HttpStatus.NOT_FOUND);
            }

            anagramas = diccionarioService.obtenerAnagramasDataBase(palabra.get());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(anagramas, HttpStatus.OK);
    }

    @PostMapping("/anagrama/list")
    public ResponseEntity<?> anagramList(@Valid @RequestBody PalabraDTO palabraRequest) {
        List<PalabraDTO> anagramas;
        try {
            Boolean existe = diccionarioService.existePalabra(palabraRequest.getPalabra());
            if (!existe) {
                return new ResponseEntity<>("Esta palabra no se encuentra en el diccionario", HttpStatus.NOT_FOUND);
            }
            anagramas = diccionarioService.obtenerAnagramasList(palabraRequest.getPalabra());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(anagramas, HttpStatus.OK);
    }


}

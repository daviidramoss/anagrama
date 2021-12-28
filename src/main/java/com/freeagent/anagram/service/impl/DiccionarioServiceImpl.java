package com.freeagent.anagram.service.impl;

import com.freeagent.anagram.dao.PalabraRepository;
import com.freeagent.anagram.dto.request.PalabraDTO;
import com.freeagent.anagram.model.Palabra;
import com.freeagent.anagram.service.DiccionarioService;
import com.freeagent.anagram.util.UtilString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DiccionarioServiceImpl implements DiccionarioService {

    PalabraRepository palabraRepository;

    List<String> diccionario;
    @Autowired
    public DiccionarioServiceImpl(PalabraRepository palabraRepository, List<String> diccionario) {
        this.palabraRepository = palabraRepository;
        this.diccionario=diccionario;
    }


    @Override
    public Optional<Palabra> obtenerPalabra(PalabraDTO palabraDTO) {
        return palabraRepository.findByPalabra(palabraDTO.getPalabra());
    }

    @Override
    public List<PalabraDTO> obtenerAnagramas(Palabra palabra) {
        List<String> palabrasArray = UtilString.arregloDeString(palabra.getPalabra());
        String palabras = String.join(",", palabrasArray);
        ;
        List<Palabra> palabrasBD = palabraRepository.obtenerAnagramas(palabras);
        List<PalabraDTO> palabraDTOS = palabrasBD.stream().map(p -> {
            PalabraDTO palabraDTO = new PalabraDTO();
            BeanUtils.copyProperties(p, palabraDTO);
            return palabraDTO;
        }).collect(Collectors.toList());
        return palabraDTOS;
    }

    @Override
    public List<PalabraDTO> obtenerAnagramasDataBase(Palabra palabra) {
        List<Palabra> palabrasBD = palabraRepository.obtenerAnagramasDataBase(UtilString.devolverOrdenado(palabra.getPalabra()));
        List<PalabraDTO> palabraDTOS = palabrasBD.stream().map(p -> {
            PalabraDTO palabraDTO = new PalabraDTO();
            BeanUtils.copyProperties(p, palabraDTO);
            return palabraDTO;
        }).collect(Collectors.toList());
        return palabraDTOS;
    }

    @Override
    public Boolean existePalabra(String palabraRequest) {
        Boolean existe = diccionario.stream().anyMatch(p -> p.equalsIgnoreCase(palabraRequest));
        return existe;
    }

    @Override
    public List<PalabraDTO> obtenerAnagramasList(String palabra) {
        List<PalabraDTO> palabras = diccionario.stream().filter(word ->
        {
            if(word.equalsIgnoreCase(palabra)) {
               return false;
            } else{
                char[] c1 = StringUtils.deleteAny(word, " ").toUpperCase().toCharArray();
                char[] c2 = StringUtils.deleteAny(palabra, " ").toUpperCase().toCharArray();
                Arrays.sort(c1);
                Arrays.sort(c2);
                return Arrays.equals(c1, c2);
            }
        }).map(word -> {
            PalabraDTO palabraDTO = new PalabraDTO();
            palabraDTO.setPalabra(word);
            return palabraDTO;
        }).collect(Collectors.toList());
        return palabras;
    }
}

package com.freeagent.anagram.service;

import com.freeagent.anagram.dto.request.PalabraDTO;
import com.freeagent.anagram.model.Palabra;

import java.util.List;
import java.util.Optional;

public interface DiccionarioService {

    Optional<Palabra> obtenerPalabra(PalabraDTO palabraDTO);
    List<PalabraDTO> obtenerAnagramas(Palabra palabra);

    List<PalabraDTO> obtenerAnagramasDataBase(Palabra palabra);

    Boolean existePalabra(String palabraRequest);

    List<PalabraDTO> obtenerAnagramasList(String palabra);
}

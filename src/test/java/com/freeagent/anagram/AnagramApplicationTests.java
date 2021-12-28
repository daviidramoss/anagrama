package com.freeagent.anagram;

import com.freeagent.anagram.controller.ApiController;
import com.freeagent.anagram.dto.request.PalabraDTO;
import com.freeagent.anagram.service.DiccionarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnagramApplicationTests {

    DiccionarioService diccionarioService;
    ApiController apiController;

    private List<String> diccionario;
    private List<PalabraDTO> palabraDTOList;
    private String palabraExiste;
    private String palabraNoExiste;
    @Autowired
    public AnagramApplicationTests(DiccionarioService diccionarioService, List<String> diccionario) {
        this.diccionarioService = diccionarioService;
        this.diccionario	= diccionario;
        apiController = new ApiController(this.diccionarioService);
        palabraDTOList  = new ArrayList<>();
        palabraDTOList.add(new PalabraDTO().setPalabra("ADDEILLMMOOORRTV"));
        palabraDTOList.add(new PalabraDTO().setPalabra("MRTaddeillmooorv"));
        palabraDTOList.add(new PalabraDTO().setPalabra("Tom Marvolo Riddle"));
        palabraDTOList.add(new PalabraDTO().setPalabra("Tom Riddle Marvolo"));
        palabraDTOList.add(new PalabraDTO().setPalabra("LORD VOLDEMORT I AM"));
        palabraDTOList.add(new PalabraDTO().setPalabra("Marvolo Riddle Tom"));
        palabraExiste = "I AM LORD VOLDEMORT";
        palabraNoExiste = "I AM NOT LORD VOLDEMORT";
    }



    @Test
    public void ServicioTest() {
        assertEquals(palabraDTOList, diccionarioService.obtenerAnagramasList(palabraExiste));
        assertTrue(diccionarioService.existePalabra(palabraExiste));
        assertFalse(diccionarioService.existePalabra(palabraNoExiste));
    }

    @Test
    public void ApiControllerTest404NotFound(){
        PalabraDTO palabraDTONoExiste = new PalabraDTO().setPalabra(palabraNoExiste);
        ResponseEntity<?> response = apiController.anagramList(palabraDTONoExiste);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void ApiControllerTest200(){
        PalabraDTO palabraDTOExiste = new PalabraDTO().setPalabra(palabraExiste);
        ResponseEntity<?> response = apiController.anagramList(palabraDTOExiste);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), palabraDTOList);
    }

}

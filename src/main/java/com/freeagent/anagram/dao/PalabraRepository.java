package com.freeagent.anagram.dao;

import com.freeagent.anagram.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Long> {

    Optional<Palabra> findByPalabra(String palabra);

    @Query("select p from Palabra p where upper(p.palabra) in (:palabras)")
    List<Palabra> obtenerAnagramas(List<String> palabras);

    @Query("select p from Palabra p where upper(p.palabra) in (:palabras)")
    List<Palabra> obtenerAnagramas(String palabras);


    @Query(value = "select * from sch_general.tb_palabra where upper((" +
            "select string_agg(c, '') as s" +
            " from (select unnest(regexp_split_to_array(regexp_replace(palabra, '\\s', '', 'g'), '')) as c" +
            " order  by c) as t)) = upper(:palabra)", nativeQuery = true)
    List<Palabra> obtenerAnagramasDataBase(String palabra);
}

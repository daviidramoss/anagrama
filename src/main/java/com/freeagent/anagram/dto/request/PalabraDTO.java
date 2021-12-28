package com.freeagent.anagram.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class PalabraDTO {

    @NotEmpty
    private String palabra;
}

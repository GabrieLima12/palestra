package com.gabriel.apresentacao.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestErroDTO {

    private String mensagem;
}

package com.RestAPIFurb.WEB2.Exception.Comanda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComandaNaoLocalizadaException extends RuntimeException {

    private final String erro;
    private final String detalhe;

    public ComandaNaoLocalizadaException(String erro, String detalhe) {
        this.erro = erro;
        this.detalhe = detalhe;
    }
}
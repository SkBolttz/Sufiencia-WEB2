package com.RestAPIFurb.WEB2.Exception.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoNaoLocalizadoException extends RuntimeException {

    private final String erro;
    private final String detalhe;

    public ProdutoNaoLocalizadoException(String erro, String detalhe) {
        this.erro = erro;
        this.detalhe = detalhe;
    }
}
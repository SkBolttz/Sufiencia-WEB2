package com.RestAPIFurb.WEB2.Exception.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroException extends RuntimeException {

    private final String erro;
    private final String detalhe;

    public CadastroException(String erro, String detalhe) {
        this.erro = erro;
        this.detalhe = detalhe;
    }
}
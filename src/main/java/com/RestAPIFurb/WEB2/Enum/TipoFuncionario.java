package com.RestAPIFurb.WEB2.Enum;

import lombok.Getter;

@Getter
public enum TipoFuncionario {
    FUNCIONARIO("Funcionario");

    private final String detalhe;

    TipoFuncionario(String detalhe) {
        this.detalhe = detalhe;
    }
}

package com.RestAPIFurb.WEB2.Exception.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroProdutoExecption extends RuntimeException {

    private final String erro;
    private final String detalhe;

    public CadastroProdutoExecption(String erro, String detalhe) {
        this.erro = erro;
        this.detalhe = detalhe;
    }
}

package com.RestAPIFurb.WEB2.DTO;

import java.util.List;

public record ComandaDTO(
                String nomeResponsavel,
                String telefoneResponsavel,
                List<ProdutoDTO> produtos) {

        @Override
        public String toString() {
                String listaProdutos = produtos.stream()
                                .map(ProdutoDTO::nomeProduto) 
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("Nenhum produto");

                return """
                                Responsável: %s
                                Telefone: %s
                                Produto(s) solicitados: %s
                                """.formatted(nomeResponsavel, telefoneResponsavel, listaProdutos);
        }
}

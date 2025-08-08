package com.RestAPIFurb.WEB2.DTO;

public record ProdutoDTO(
        String nomeProduto,
        Double precoProduto,
        Integer quantidade) {
}

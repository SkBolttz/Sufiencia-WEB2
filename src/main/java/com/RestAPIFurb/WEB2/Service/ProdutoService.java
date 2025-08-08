package com.RestAPIFurb.WEB2.Service;

import org.springframework.stereotype.Service;
import com.RestAPIFurb.WEB2.DTO.ProdutoDTO;
import com.RestAPIFurb.WEB2.Entity.Produto;
import com.RestAPIFurb.WEB2.Exception.Produto.DuplicidadeCadastroException;
import com.RestAPIFurb.WEB2.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(ProdutoDTO produto) {

        Produto produtoExiste = produtoRepository.findByNomeProduto(produto.nomeProduto()).orElse(null);

        if (produtoExiste != null) {
            throw new DuplicidadeCadastroException("Erro no cadastro", "Produto ja cadastrado!");
        }

        Produto produtoEntity = (new Produto(null, produto.nomeProduto(), produto.precoProduto()));
        produtoRepository.save(produtoEntity);
    }
}

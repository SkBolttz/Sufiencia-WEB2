package com.RestAPIFurb.WEB2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RestAPIFurb.WEB2.DTO.ProdutoDTO;
import com.RestAPIFurb.WEB2.Exception.Produto.CadastroProdutoExecption;
import com.RestAPIFurb.WEB2.Exception.Produto.DuplicidadeCadastroException;
import com.RestAPIFurb.WEB2.Service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/RestAPIFurb")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto/cadastrar")
    @Operation(summary = "Cadastro de produtos", description = "Endpoint utilizado para realizar o cadastro de produtos disponiveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar produto")
    })
    @Parameters({
            @Parameter(name = "nomeProduto", required = true, description = "Nome do produto"),
            @Parameter(name = "precoProduto", required = true, description = "Preco do produto")
    })
    public ResponseEntity<String> cadastroProduto(@RequestBody @Valid ProdutoDTO produto) {
        try {
            produtoService.cadastrarProduto(produto);
            return ResponseEntity.status(201).body(produto.nomeProduto()
                    + " cadastrado com sucesso no valor de: R$" + produto.precoProduto());
        } catch (CadastroProdutoExecption e) {
            return ResponseEntity.status(400).body("Erro ao cadastrar produto: " + produto.nomeProduto());
        } catch (DuplicidadeCadastroException e) {
            return ResponseEntity.status(400).body("Produto ja cadastrado em sistema: " + produto.nomeProduto());
        }
    }
}

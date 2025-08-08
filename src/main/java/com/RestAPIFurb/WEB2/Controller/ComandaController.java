package com.RestAPIFurb.WEB2.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RestAPIFurb.WEB2.DTO.ComandaDTO;
import com.RestAPIFurb.WEB2.Entity.Comanda;
import com.RestAPIFurb.WEB2.Exception.Comanda.ComandaNaoLocalizadaException;
import com.RestAPIFurb.WEB2.Exception.Comanda.RegistroComandaException;
import com.RestAPIFurb.WEB2.Exception.Comanda.UsuarioNaoLocalizadoException;
import com.RestAPIFurb.WEB2.Exception.Produto.ProdutoNaoLocalizadoException;
import com.RestAPIFurb.WEB2.Service.ComandaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RequestMapping("/RestAPIFurb")
@RestController
public class ComandaController {

    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping("/registrar/comanda")
    @Operation(summary = "Registro de comanda", description = "Endpoint utilizado para registrar uma nova comanda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comanda cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar comanda"),
            @ApiResponse(responseCode = "404", description = "Erro ao localizar usuário ou produto")
    })
    @Parameters({
            @Parameter(name = "preco", required = true, description = "Preco da comanda"),
            @Parameter(name = "data", required = true, description = "Data da comanda"),
            @Parameter(name = "nomeResponsavel", required = true, description = "Nome do responsavel da comanda"),
            @Parameter(name = "telefoneResponsavel", required = true, description = "Telefone do responsavel da comanda")
    })
    public ResponseEntity<String> registrarComanda(@RequestBody @Valid ComandaDTO comanda) {
        try {
            comandaService.registrarComanda(comanda);
            return ResponseEntity.status(201).body("Comanda cadastrada com sucesso!" + "\n" + comanda);
        } catch (RegistroComandaException e) {
            return ResponseEntity.status(400).body("Erro ao cadastrar comanda!");
        } catch (UsuarioNaoLocalizadoException e) {
            return ResponseEntity.status(404).body("Erro ao localizar usuário!");
        } catch (ProdutoNaoLocalizadoException e) {
            return ResponseEntity.status(404).body("Erro ao localizar produto!");
        }
    }

    @GetMapping("/listar/comandas")
    @Operation(summary = "Listar comandas", description = "Endpoint utilizado para listar todas as comandas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de comandas recuperada com sucesso")
    })
    public ResponseEntity<List<Comanda>> listarComandas() {
        return ResponseEntity.status(200).body(comandaService.listarComandas());
    }

    @GetMapping("/listar/comanda/{id}")
    @Operation(summary = "Listar comanda", description = "Endpoint utilizado para listar uma comanda registrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comanda recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comanda não encontrada")
    })
    public ResponseEntity<Comanda> listarComanda(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(comandaService.listarComandaPorId(id));
        } catch (ComandaNaoLocalizadaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/editar/comanda/{id}")
    @Operation(summary = "Editar comanda", description = "Endpoint utilizado para editar uma comanda registrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comanda editada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao localizar comanda ou produto")
    })
    public ResponseEntity<String> editarComanda(@PathVariable Long id, @RequestBody @Valid ComandaDTO comanda) {
        try {
            comandaService.editarComanda(id, comanda);
            return ResponseEntity.status(200).body("Comanda editada com sucesso!");
        } catch (ComandaNaoLocalizadaException e) {
            return ResponseEntity.status(404).body("Comanda não encontrada!");
        } catch (ProdutoNaoLocalizadoException e) {
            return ResponseEntity.status(404).body("Erro ao localizar produto!");
        }
    }

    @DeleteMapping("/deletar/comanda/{id}")
    @Operation(summary = "Deletar comanda", description = "Endpoint utilizado para deletar uma comanda registrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comanda deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao localizar comanda")
    })
    public ResponseEntity<String> deletarComanda(@PathVariable Long id) {
        try {
            comandaService.deletarComanda(id);
            return ResponseEntity.status(200).body("Comanda deletada com sucesso!");
        } catch (ComandaNaoLocalizadaException e) {
            return ResponseEntity.status(404).body("Erro ao localizar comanda");
        }
    }
}

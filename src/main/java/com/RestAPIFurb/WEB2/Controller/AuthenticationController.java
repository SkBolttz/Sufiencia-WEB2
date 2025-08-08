package com.RestAPIFurb.WEB2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RestAPIFurb.WEB2.DTO.LoginDTO;
import com.RestAPIFurb.WEB2.Entity.Funcionario;
import com.RestAPIFurb.WEB2.Exception.Funcionario.CadastroException;
import com.RestAPIFurb.WEB2.Security.TokenJWT;
import com.RestAPIFurb.WEB2.Security.TokenService;
import com.RestAPIFurb.WEB2.Service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/RestAPIFurb")
@Tag(name = "Autenticação", description = "Controller responsável por gerenciar as informações de autenticação de funcionaários.")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Efetua o login do funcionário.")
    @Parameters({
            @Parameter(name = "cpf", description = "CPF do funcionário."),
            @Parameter(name = "senha", description = "Senha do funcionário.")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao efetuar login.")
    })
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.cpf(), loginDTO.senha());

        Authentication authentication = authManager.authenticate(authenticationToken);

        Funcionario funcionario = (Funcionario) authentication.getPrincipal();
        String token = tokenService.gerarToken(funcionario);

        return ResponseEntity.ok(new TokenJWT(token));
    }

    @PostMapping("/registro")
    @Operation(summary = "Cadastro", description = "Efetua o cadastro do funcionário.")
    @Parameters({
            @Parameter(name = "funcionário", description = "Informações do funcionário para cadastro."),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados incorretos no cadastro ou CPF de funcionario ja cadastrado.")
    })
    public ResponseEntity<?> registrar(@RequestBody @Valid Funcionario funcionario) {
        try {
            loginService.registro(funcionario);
            return ResponseEntity.status(200).body("Cadastro realizado com sucesso!");
        } catch (CadastroException e) {
            return ResponseEntity.status(400).body("CPF de funcionário ja cadastrado!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao realizar cadastro!");
        }
    }
}

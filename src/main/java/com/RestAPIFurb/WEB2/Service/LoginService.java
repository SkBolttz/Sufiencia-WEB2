package com.RestAPIFurb.WEB2.Service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RestAPIFurb.WEB2.Entity.Funcionario;
import com.RestAPIFurb.WEB2.Enum.TipoFuncionario;
import com.RestAPIFurb.WEB2.Exception.Funcionario.CadastroException;
import com.RestAPIFurb.WEB2.Repository.FuncionarioRepository;

@Service
public class LoginService {

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registro(Funcionario funcionario) {

        Funcionario funcionarioExiste = funcionarioRepository.findByCpf(funcionario.getCpf()).orElse(null);

        if (funcionarioExiste != null) {
            throw new CadastroException("Erro no cadastro", "Funcionario ja cadastrado");
        }

        funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));
        funcionario.setDataCadastrado(LocalDate.now());
        funcionario.setTipoFuncionario(TipoFuncionario.FUNCIONARIO);

        funcionarioRepository.save(funcionario);
    }

}

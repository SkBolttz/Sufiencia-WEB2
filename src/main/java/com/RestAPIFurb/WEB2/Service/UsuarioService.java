package com.RestAPIFurb.WEB2.Service;

import org.springframework.stereotype.Service;
import com.RestAPIFurb.WEB2.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository userRepository;

    public UsuarioService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }
}

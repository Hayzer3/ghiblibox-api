package com.ghiblibox.api.controller;

import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.repository.UsuarioRepository;
import com.ghiblibox.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity efetuarCadastro(@RequestBody DadosCadastroUsuario dados) {
        if (repository.findByEmail(dados.email()) != null) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        Usuario novoUsuario = new Usuario(
                null,
                dados.username(),
                dados.email(),
                senhaCriptografada,
                dados.dataNascimento(),
                dados.genero(),
                0,
                0,
                0,
                0,
                new java.util.HashSet<>(), // lista de quem ele segue
                new java.util.HashSet<>()  // lista de seguidores
        );

        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}

record DadosAutenticacao(String email, String senha) {}
record DadosCadastroUsuario(String username, String email, String senha, LocalDate dataNascimento, String genero) {}
record DadosTokenJWT(String token) {}
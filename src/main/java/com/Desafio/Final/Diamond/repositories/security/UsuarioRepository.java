package com.Desafio.Final.Diamond.repositories.security;

import com.Desafio.Final.Diamond.users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByName(String username);
}

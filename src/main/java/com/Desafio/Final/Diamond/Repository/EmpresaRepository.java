package com.Desafio.Final.Diamond.Repositorys;

import com.Desafio.Final.Diamond.Models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer>{

    Optional<EmpresaModel> findByid(Integer id);

    void delete(Integer empresaid);

}

package com.diaz.denunciaservice.repository;

import com.diaz.denunciaservice.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    @Query(value = "SELECT * FROM denuncias ORDER BY id DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<Denuncia> listar(int offset, int limit);

    List<Denuncia> findByDni(String dni);
}

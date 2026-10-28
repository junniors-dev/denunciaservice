package com.diaz.denunciaservice.repository;

import com.diaz.denunciaservice.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    // offset cuenta registros, no páginas: por eso LIMIT/OFFSET y no Pageable
    @Query(value = "SELECT * FROM denuncias ORDER BY id DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Denuncia> listarPaginado(@Param("offset") int offset, @Param("limit") int limit);

    List<Denuncia> findByDniOrderByIdDesc(String dni);
}

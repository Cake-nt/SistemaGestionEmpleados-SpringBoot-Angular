package com.toledo.empleados.repository;

import com.toledo.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    // Consulta nativa con esquema explícito
    @Query(value = "SELECT * FROM dbo.empleado", nativeQuery = true)
    List<Empleado> findAllNative();
    
    // Consulta nativa para contar
    @Query(value = "SELECT COUNT(*) FROM dbo.empleado", nativeQuery = true)
    long countNative();
}
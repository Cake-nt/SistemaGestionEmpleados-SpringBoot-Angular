package com.toledo.empleados.service;  // ← Cambio

import com.toledo.empleados.dto.EmpleadoDTO;
import java.util.List;

public interface EmpleadoService {
    List<EmpleadoDTO> listarTodos();
    EmpleadoDTO buscarPorId(Long id);
    EmpleadoDTO crear(EmpleadoDTO empleadoDTO);
    EmpleadoDTO actualizar(Long id, EmpleadoDTO empleadoDTO);
    void eliminar(Long id);
}
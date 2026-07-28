package com.toledo.empleados.service.impl;

import com.toledo.empleados.dto.EmpleadoDTO;
import com.toledo.empleados.model.Empleado;
import com.toledo.empleados.repository.EmpleadoRepository;
import com.toledo.empleados.service.EmpleadoService;
import com.toledo.empleados.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    public List<EmpleadoDTO> listarTodos() {
        return empleadoRepository.findAll()
            .stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public EmpleadoDTO buscarPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        return convertirADTO(empleado);
    }
    
    @Override
    public EmpleadoDTO crear(EmpleadoDTO empleadoDTO) {
        Empleado empleado = convertirAEntidad(empleadoDTO);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return convertirADTO(empleadoGuardado);
    }
    
    @Override
    public EmpleadoDTO actualizar(Long id, EmpleadoDTO empleadoDTO) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        
        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setApellido(empleadoDTO.getApellido());
        empleadoExistente.setEmail(empleadoDTO.getEmail());
        empleadoExistente.setFechaContratacion(empleadoDTO.getFechaContratacion());
        
        Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
        return convertirADTO(empleadoActualizado);
    }
    
    @Override
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empleado no encontrado con id: " + id);
        }
        empleadoRepository.deleteById(id);
    }
    
    // Métodos auxiliares para convertir entre entidad y DTO
    private EmpleadoDTO convertirADTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setEmail(empleado.getEmail());
        dto.setFechaContratacion(empleado.getFechaContratacion());
        return dto;
    }
    
    private Empleado convertirAEntidad(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setEmail(dto.getEmail());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return empleado;
    }
}
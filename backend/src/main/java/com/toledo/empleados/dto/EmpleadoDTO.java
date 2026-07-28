package com.toledo.empleados.dto;

import java.time.LocalDate;

public class EmpleadoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaContratacion;
    
    // Constructor vacío
    public EmpleadoDTO() {}
    
    // Constructor con todos los campos
    public EmpleadoDTO(Long id, String nombre, String apellido, String email, LocalDate fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
}
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpleadoService } from '../../services/empleado.service';
import { Empleado } from '../../models/empleado.model';
import { EmpleadoFormComponent } from '../empleado-form/empleado-form.component';

@Component({
  selector: 'app-empleado-list',
  standalone: true,
  imports: [CommonModule, EmpleadoFormComponent],
  templateUrl: './empleado-list.component.html',
  styleUrls: ['./empleado-list.component.css']
})
export class EmpleadoListComponent implements OnInit {
  empleados: Empleado[] = [];
  empleadoSeleccionado: Empleado | null = null;
  mostrarFormulario = false;

  constructor(private empleadoService: EmpleadoService) { }

  ngOnInit(): void {
    console.log('🚀 ngOnInit ejecutado - Cargando empleados...');  // ← DEPURACIÓN
    this.cargarEmpleados();
  }

  cargarEmpleados(): void {
    console.log('📡 Llamando a la API...');  // ← DEPURACIÓN
    this.empleadoService.getEmpleados().subscribe({
      next: (data) => {
        console.log('✅ Datos recibidos:', data);  // ← DEPURACIÓN
        this.empleados = data;
      },
      error: (error) => {
        console.error('❌ Error al cargar empleados:', error);
        alert('Error al cargar la lista de empleados');
      }
    });
  }

  eliminarEmpleado(id: number): void {
    if (confirm('¿Estás seguro de eliminar este empleado?')) {
      this.empleadoService.deleteEmpleado(id).subscribe({
        next: () => {
          this.cargarEmpleados();
          alert('Empleado eliminado correctamente');
        },
        error: (error) => {
          console.error('Error al eliminar empleado:', error);
          alert('Error al eliminar el empleado');
        }
      });
    }
  }

  editarEmpleado(empleado: Empleado): void {
    this.empleadoSeleccionado = { ...empleado };
    this.mostrarFormulario = true;
  }

  crearEmpleado(): void {
    this.empleadoSeleccionado = null;
    this.mostrarFormulario = true;
  }

  cerrarFormulario(): void {
    this.mostrarFormulario = false;
    this.empleadoSeleccionado = null;
  }

  guardarEmpleado(empleado: Empleado): void {
    if (empleado.id) {
      this.empleadoService.updateEmpleado(empleado.id, empleado).subscribe({
        next: () => {
          this.cargarEmpleados();
          this.cerrarFormulario();
          alert('Empleado actualizado correctamente');
        },
        error: (error) => {
          console.error('Error al actualizar empleado:', error);
          alert('Error al actualizar el empleado');
        }
      });
    } else {
      this.empleadoService.createEmpleado(empleado).subscribe({
        next: () => {
          this.cargarEmpleados();
          this.cerrarFormulario();
          alert('Empleado creado correctamente');
        },
        error: (error) => {
          console.error('Error al crear empleado:', error);
          alert('Error al crear el empleado');
        }
      });
    }
  }
}
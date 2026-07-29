import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Empleado } from '../../models/empleado.model';

@Component({
  selector: 'app-empleado-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './empleado-form.component.html',
  styleUrls: ['./empleado-form.component.css']
})
export class EmpleadoFormComponent implements OnInit {
  @Input() empleado: Empleado | null = null;
  @Output() guardar = new EventEmitter<Empleado>();
  @Output() cancelar = new EventEmitter<void>();

  empleadoForm!: FormGroup;
  esEdicion = false;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.esEdicion = !!this.empleado;
    this.empleadoForm = this.fb.group({
      id: [this.empleado?.id || null],
      nombre: [this.empleado?.nombre || '', Validators.required],
      apellido: [this.empleado?.apellido || '', Validators.required],
      email: [this.empleado?.email || '', [Validators.required, Validators.email]],
      fechaContratacion: [this.empleado?.fechaContratacion || '', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.empleadoForm.valid) {
      this.guardar.emit(this.empleadoForm.value);
    }
  }

  onCancel(): void {
    this.cancelar.emit();
  }
}
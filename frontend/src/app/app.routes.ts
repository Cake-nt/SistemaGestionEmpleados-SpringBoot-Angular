import { Routes } from '@angular/router';
import { EmpleadoListComponent } from './components/empleado-list/empleado-list.component';

export const routes: Routes = [
  { path: '', component: EmpleadoListComponent },
  { path: 'empleados', component: EmpleadoListComponent }
];
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TransferenciasComponent } from './components/transferencias/transferencias.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TransferenciasComponent],
  template: `<app-transferencias></app-transferencias>`
})
export class AppComponent {}

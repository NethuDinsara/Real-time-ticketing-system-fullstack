import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-log-display',
  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.css'
})
export class LogDisplayComponent {
  @Input() logs: string[] = []; // to receive logs
}

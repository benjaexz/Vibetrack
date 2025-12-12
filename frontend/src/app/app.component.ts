import { Component } from '@angular/core';
import { AuthService } from './core/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  username = '';
  password = '';
  message = '';

  constructor(private authService: AuthService) {}

  onLogin(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        this.message = res.message || 'Login ok.';
      },
      error: () => {
        this.message = 'Falha no login.';
      }
    });
  }
}

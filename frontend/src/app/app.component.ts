import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';  // ← ADICIONA ESSE IMPORT
import { VibeService } from './vibe.service';

@Component({
selector: 'app-root',
standalone: true,
imports: [CommonModule, FormsModule, HttpClientModule, RouterOutlet],  // ← ADICIONA RouterOutlet AQUI
templateUrl: './app.component.html',
styleUrls: ['./app.component.css'],
})
export class AppComponent {
vibes: any[] = [];

newVibe: any = {
musica: '',
artista: '',
genero: '',
emocao: '',
timestamp: '',
userId: 1
};

constructor(private vibeService: VibeService) {}

  ngOnInit() {
    this.loadVibes();
  }

  loadVibes() {
    this.vibeService.getAll().subscribe({
      next: (data) => (this.vibes = data),
      error: (err) => console.error(err),
    });
  }

  criarVibe() {
    this.vibeService.create(this.newVibe).subscribe({
      next: () => {
        this.newVibe = {
          musica: '',
          artista: '',
          genero: '',
          emocao: '',
          timestamp: '',
          userId: 1
        };
        this.loadVibes();
      },
      error: (err) => console.error(err),
    });
  }

  deletar(id: number) {
    this.vibeService.delete(id).subscribe({
      next: () => this.loadVibes(),
      error: (err) => console.error(err),
    });
  }
}

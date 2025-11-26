import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsService } from '../../services/stats.service';
import { EmotionStats, TopItem, TimelineEntry } from '../../models/stats.model';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  emotionStats: EmotionStats[] = [];
  topGenres: TopItem[] = [];
  topArtists: TopItem[] = [];
  timeline: TimelineEntry[] = [];

  loading = true;
  error: string | null = null;

  emotionChart: any;
  genresChart: any;
  timelineChart: any;

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    this.loadAllStats();
  }

  loadAllStats(): void {
    this.loading = true;

    // Carregar estatísticas de emoções
    this.statsService.getEmotionStats().subscribe({
      next: (data) => {
        this.emotionStats = data;
        this.createEmotionChart();
      },
      error: (err) => {
        this.error = 'Erro ao carregar estatísticas de emoções';
        console.error(err);
      }
    });

    // Carregar top gêneros
    this.statsService.getTopGenres().subscribe({
      next: (data) => {
        this.topGenres = data;
        this.createGenresChart();
      },
      error: (err) => {
        this.error = 'Erro ao carregar gêneros';
        console.error(err);
      }
    });

    // Carregar top artistas
    this.statsService.getTopArtists().subscribe({
      next: (data) => {
        this.topArtists = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar artistas';
        console.error(err);
        this.loading = false;
      }
    });

    // Carregar timeline
    this.statsService.getTimeline(7).subscribe({
      next: (data) => {
        this.timeline = data;
        this.createTimelineChart();
      },
      error: (err) => {
        this.error = 'Erro ao carregar timeline';
        console.error(err);
      }
    });
  }

  createEmotionChart(): void {
    const canvas = document.getElementById('emotionChart') as HTMLCanvasElement;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    if (this.emotionChart) {
      this.emotionChart.destroy();
    }

    this.emotionChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: this.emotionStats.map(e => e.emotion),
        datasets: [{
          data: this.emotionStats.map(e => e.count),
          backgroundColor: [
            '#FFD700',
            '#4169E1',
            '#DC143C',
            '#32CD32',
            '#9370DB'
          ]
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom'
          },
          title: {
            display: true,
            text: 'Emoções'
          }
        }
      }
    });
  }

  createGenresChart(): void {
    const canvas = document.getElementById('genresChart') as HTMLCanvasElement;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    if (this.genresChart) {
      this.genresChart.destroy();
    }

    this.genresChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: this.topGenres.map(g => g.name),
        datasets: [{
          label: 'Quantidade',
          data: this.topGenres.map(g => g.count),
          backgroundColor: '#667eea'
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          },
          title: {
            display: true,
            text: 'Top Gêneros'
          }
        }
      }
    });
  }

  createTimelineChart(): void {
    const canvas = document.getElementById('timelineChart') as HTMLCanvasElement;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    if (this.timelineChart) {
      this.timelineChart.destroy();
    }

    this.timelineChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.timeline.map(t => t.date),
        datasets: [{
          label: 'Vibes por dia',
          data: this.timeline.map(t => t.count),
          borderColor: '#764ba2',
          backgroundColor: 'rgba(118, 75, 162, 0.1)',
          tension: 0.4
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          },
          title: {
            display: true,
            text: 'Timeline (últimos 7 dias)'
          }
        }
      }
    });
  }
}

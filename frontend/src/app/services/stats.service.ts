import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmotionStats, TopItem, TimelineEntry } from '../models/stats.model';

@Injectable({
providedIn: 'root'
})
export class StatsService {

private apiUrl = 'http://localhost:8080/api/stats';

constructor(private http: HttpClient) {}

  getEmotionStats(): Observable<EmotionStats[]> {
    return this.http.get<EmotionStats[]>(`${this.apiUrl}/emotions`);
  }

  getTopGenres(): Observable<TopItem[]> {
    return this.http.get<TopItem[]>(`${this.apiUrl}/genres`);
  }

  getTopArtists(): Observable<TopItem[]> {
    return this.http.get<TopItem[]>(`${this.apiUrl}/artists`);
  }

  getTimeline(days: number = 7): Observable<TimelineEntry[]> {
    return this.http.get<TimelineEntry[]>(`${this.apiUrl}/timeline?days=${days}`);
  }
}

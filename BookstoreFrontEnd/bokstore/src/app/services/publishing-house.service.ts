import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PublishingHouse } from '../models/publishing-house.model';

const baseUrl = 'http://localhost:8080/publishingHouses';

@Injectable({
  providedIn: 'root'
})
export class PublishingHouseService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<PublishingHouse[]> {
    return this.http.get<PublishingHouse[]>(baseUrl);
  }

  get(id: any): Observable<PublishingHouse> {
    return this.http.get<PublishingHouse>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByName(name: any): Observable<PublishingHouse[]> {
    return this.http.get<PublishingHouse[]>(`${baseUrl}?name=${name}`);
  }
}
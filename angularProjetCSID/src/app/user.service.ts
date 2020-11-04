import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from './user-edit/user';
import {map} from 'rxjs/operators';

interface ReturnedId { createdId: string; }

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpclient: HttpClient) { }

  register(value: object): Observable<string> {
    return this.httpclient.post<ReturnedId>('http://localhost:8000/users', value).pipe(map((result) => {
        return result.createdId;
      })); }

  getUser(id: string): Observable<User> {
    return this.httpclient.get<User>('http://localhost:8000/users/' + id);
  }
}

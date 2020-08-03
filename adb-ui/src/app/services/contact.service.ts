import { Injectable } from '@angular/core';
import { Contact } from '../models/contact';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ContactService {

    api: string = environment.api;

    constructor(private httpClient: HttpClient) {
    }

    contactUs(contact: Contact): Observable<any> {
        return this.httpClient.post<any>(this.api + 'contactUs', user);
    }


}

import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Contact } from '../models/contact';

@Injectable({
  providedIn: 'root',
})
export class ContactService {

    api: string = environment.api;

    constructor(private httpClient: HttpClient) {
    }

   contactAdb(contactEmail: Contact): Observable<any> {
        console.log("Sending contact email: ", contactEmail);
        return this.httpClient.post<any>(this.api + 'email/sendContactEmail', contactEmail);
    }

}

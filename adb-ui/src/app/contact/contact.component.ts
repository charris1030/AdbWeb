import { Component } from '@angular/core';
import { Contact } from '../models/contact';
import { ContactService } from './contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent {
    showRequired: boolean = false;
    isFNameValid: boolean = true;
    isLNameValid: boolean = true;
    isEmailValid: boolean = true;
    isPhoneValid: boolean = true;
    contactFailed: boolean = false;
    contactSuccess: boolean = false;
    submittingData: boolean = false;
    isProjectDetailsValid: boolean = true;
    contact: Contact = new Contact();

    constructor(public contactService: ContactService){
    }

    contactAdb() {
    this.submittingData = true;
        this.isFNameValid = true;
        this.isLNameValid = true;
        this.isEmailValid = true;
        this.isPhoneValid = true;
        this.isProjectDetailsValid = true;
        this.contactFailed = false;
      if(this.contact.firstName == null || this.contact.firstName == '') {
        this.isFNameValid = false;
      }
      if(this.contact.lastName == null || this.contact.lastName == '') {
         this.isLNameValid = false;
       }
      if(this.contact.email == null || this.contact.email == '') {
         this.isEmailValid = false;
        }
      if(this.contact.projectDetails == null || this.contact.projectDetails == '') {
         this.isProjectDetailsValid = false;
       }
        if(!this.isFNameValid ||  !this.isLNameValid ||  !this.isEmailValid ||  !this.isPhoneValid || !this.isProjectDetailsValid) {
          this.submittingData = false;
          this.contactSuccess = false;
          return;
        }

          this.contactService.contactAdb(this.contact).subscribe(
              res => {
              console.log("Send Contact Email Response: ", res);
                if(res) {
                  console.log(res);
                  if(res != null) {
                    this.submittingData = false;
                    this.contactSuccess = true;
                    this.contact = new Contact();
                  } else {
                      this.contactSuccess = true;
                      this.submittingData = false;
                  }
                }
              },
              (err) => {
                this.submittingData = false;
                console.error(err);
                this.contactFailed = true;
              });

    }
}

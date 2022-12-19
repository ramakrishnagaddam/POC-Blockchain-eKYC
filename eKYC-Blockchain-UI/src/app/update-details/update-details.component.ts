import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/api.service';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-details',
  templateUrl: './update-details.component.html',
  styleUrls: ['./update-details.component.css']
})
export class UpdateDetailsComponent implements OnInit {

  regulatorBody: FormGroup;
  error = null;
  hexCode = null;
  data = null;
  constructor( private __formBuilder: FormBuilder,
               private __apiService: ApiService,
               private __routerProvide: Router
              ) { }

  ngOnInit() {
    if (sessionStorage.getItem('sessionKey') === null) {
      this.__routerProvide.navigate(['']);
    }
  }

  onSearch() {
    this.__apiService.executeGetDataUsingHex(this.hexCode).subscribe(
      data => {
        this.data = data.data;
        this.refresh(this.data);
      },
      error => {
        this.error = error;
        this.data = null;
      }
    );
  }

  refresh(data) {
    this.regulatorBody = this.__formBuilder.group({
      firstName: [ data.firstName ],
      middleName: [ data.middleName ],
      lastName: [ data.lastName ],
      dateOfBirth: [ data.dateOfBirth ],
      email: [ data.email ],
      mobile: [ data.mobile ],
      addressLine1: [ data.addressLine1 ],
      addressLine2: [ data.addressLine2 ],
      city: [ data.city ],
      state: [ data.state ],
      country: [ 'IND' ],
      zipCode: [ data.zipCode ],
      idType: this.__formBuilder.array([this.idProofType(data.idType[0])])
    });
    for ( let i = 1; i < data.idType.length; i++ ) {
      this.increment(data.idType[i]);
    }
  }

  idProofType(data: any): FormGroup {
    return this.__formBuilder.group({
      idNumber: [ data.idNumber ],
      type: [ data.type ],
      description: [ data.description ]
    });
  }

  increment(data) {
    (<FormArray>this.regulatorBody.get('idType')).push(this.idProofType(data));
  }
}

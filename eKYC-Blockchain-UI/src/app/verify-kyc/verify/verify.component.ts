import { Component, OnInit, Inject } from '@angular/core';
import { ApiService } from 'src/app/api.service';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent implements OnInit {

  success = null;
  error = null;
  submitted = false;
  regulatorBody: FormGroup;
  kycDetails: any;
  constructor( @Inject(MAT_DIALOG_DATA) public data: any,
               private __formBuilder: FormBuilder,
               private __dialogRef: MatDialogRef<VerifyComponent>,
               private __apiService: ApiService ) {
                 this.kycDetails = this.data.data;
               }

  ngOnInit() {
    this.regulatorBody = this.__formBuilder.group({
      firstName: [ this.kycDetails.firstName ],
      middleName: [ this.kycDetails.middleName ],
      lastName: [ this.kycDetails.lastName ],
      dateOfBirth: [ this.kycDetails.dateOfBirth ],
      email: [ this.kycDetails.email ],
      mobile: [ this.kycDetails.mobile ],
      addressLine1: [ this.kycDetails.addressLine1 ],
      addressLine2: [ this.kycDetails.addressLine2 ],
      city: [ this.kycDetails.city ],
      state: [ this.kycDetails.state ],
      country: [ 'IND' ],
      primaryIdNumber: [ this.kycDetails.primaryIdNumber ],
      primaryIdType: [ this.kycDetails.primaryIdType ],
      zipCode: [ this.kycDetails.zipCode ],
      notes: ['', [ Validators.required, Validators.minLength(5), Validators.maxLength(1000),
        Validators.pattern('^(?=.*[a-zA-Z])[a-zA-Z0-9 *&^%$#@!]+$') ] ],
      idType: this.__formBuilder.array([this.idProofType(this.kycDetails.idType[0])])
    });
    for ( let i = 1; i < this.kycDetails.idType.length; i++ ) {
      this.increment(i);
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
    (<FormArray>this.regulatorBody.get('idType')).push(this.idProofType(this.kycDetails.idType[data]));
  }

  verifyKYC(data) {
    this.__apiService.executeVerifyKyc(this.regulatorBody.value, data).subscribe(
      result => {
        this.success = result.data;
      },
      error => {
        this.error = error;
      }
    );
  }
}

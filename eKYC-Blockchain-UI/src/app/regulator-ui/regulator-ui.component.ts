import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormGroup } from '@angular/forms';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-regulator-ui',
  templateUrl: './regulator-ui.component.html',
  styleUrls: ['./regulator-ui.component.css']
})
export class RegulatorUiComponent implements OnInit {
  error = null;
  success = null;
  regulatorBody: FormGroup;
  constructor( private __formBuilder: FormBuilder,
               private __apiService: ApiService ) { }

  ngOnInit() {
    this.regulatorBody = this.__formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(150)] ],
      middleName: [''],
      lastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(150)] ],
      dateOfBirth: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10)] ],
      email: [''],
      mobile: ['', [Validators.required, Validators.pattern('[0-9]{10, 12}') ] ],
      addressLine1: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(150)] ],
      addressLine2: [''],
      city: [ '', [Validators.required, Validators.minLength(3), Validators.maxLength(150)] ],
      state: [ '', [Validators.required, Validators.minLength(5), Validators.maxLength(150)] ],
      country: [ 'IND', [Validators.required, Validators.minLength(3), Validators.maxLength(150)] ],
      zipCode: [ '', [Validators.required, Validators.pattern('[0-9]{4, 10}') ] ],
      idType: this.__formBuilder.array([this.idProofType()])
    });
  }

  idProofType(): FormGroup {
    return this.__formBuilder.group({
      idNumber: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(150)] ],
      type: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(150)] ],
      description: ['']
    });
  }

  increment() {
    (<FormArray>this.regulatorBody.get('idType')).push(this.idProofType());
  }

  delete(value: number) {
    (<FormArray>this.regulatorBody.get('idType')).removeAt(value);
  }

  submitRegulatory() {
    this.error = null;
    this.success = null;
    const value = this.regulatorBody.value;
    this.__apiService.submitKyc(value).subscribe(
      data => {
        this.success = data.data;
        this.regulatorBody.reset();
      },
      error => {
        this.error = error;
      }
    );
  }
}

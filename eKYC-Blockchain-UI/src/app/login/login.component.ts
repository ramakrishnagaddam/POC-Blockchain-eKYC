import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  error = null;

  constructor( private __formBuilder: FormBuilder,
               private __apiService: ApiService,
               private __routerProvide: Router ) { }

  ngOnInit() {
    if (sessionStorage.getItem('sessionKey') !== null) {
      this.__routerProvide.navigate(['dashboard']);
    }
    this.loginForm = this.__formBuilder.group({
      userid : ['', [ Validators.required, Validators.minLength(4), Validators.maxLength(20) ]],
      password : ['', [ Validators.required, Validators.minLength(4), Validators.maxLength(15) ]]
    });
  }

  SignIn() {

    const userName = this.loginForm.controls['userid'].value;
    const password = this.loginForm.controls['password'].value;
    if ( (userName.trim() !== '' || userName.trim() !== 'undefined') && ( password.trim() !== '' || password.trim() !== 'undefined' )) {
      if ( (userName === 'regulatory') && (password === 'regulatory')) {
        sessionStorage.setItem('sessionKey', 'aerasdSADFasdFASDasdfasdrASR' );
      }
      if ( (userName === 'bankuser') && (password === 'bankuser')) {
        sessionStorage.setItem('sessionKey', 'redasfaweADSfasdfewfasdfdaffaddsaB' );
      }
      if ( (userName === 'user') && (password === 'user')) {
        sessionStorage.setItem('sessionKey', 'vasdfewasadfasdfSAdfasdfdasfasdfU' );
      }
      this.__apiService.loggedIn(true);
      this.__routerProvide.navigate(['dashboard']);
    } else {
      this.error = 'Invaid username/password';
    }
  }
}

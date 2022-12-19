import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RegulatorUiComponent } from './regulator-ui/regulator-ui.component';
import { VerifyKYCComponent } from './verify-kyc/verify-kyc.component';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { VerifyComponent } from './verify-kyc/verify/verify.component';
import { UpdateDetailsComponent } from './update-details/update-details.component';
import { HeaderComponent } from './shared/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    RegulatorUiComponent,
    VerifyKYCComponent,
    VerifyComponent,
    UpdateDetailsComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  providers: [],
  entryComponents: [ VerifyComponent ],
  bootstrap: [AppComponent]
})
export class AppModule { }

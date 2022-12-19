import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from 'src/app/login/login.component';
import { DashboardComponent } from 'src/app/dashboard/dashboard.component';
import { VerifyKYCComponent } from 'src/app/verify-kyc/verify-kyc.component';
import { UpdateDetailsComponent } from 'src/app/update-details/update-details.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'verify', component: VerifyKYCComponent },
  { path: 'kycDetails', component: UpdateDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

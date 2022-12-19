import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userType: string;
  constructor( private __apiService: ApiService,
               private __routerProvide: Router
             ) { }

  ngOnInit() {
    if (sessionStorage.getItem('sessionKey') === null) {
      this.__routerProvide.navigate(['']);
    }
    const session: any = sessionStorage.getItem('sessionKey');
    this.userType = session.slice(-1);
    console.log(session.slice(-1));
  }

}

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ApiService } from 'src/app/api.service';
import { VerifyComponent } from 'src/app/verify-kyc/verify/verify.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verify-kyc',
  templateUrl: './verify-kyc.component.html',
  styleUrls: ['./verify-kyc.component.css']
})
export class VerifyKYCComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  dataSource;
  dataLength;
  error = null;

  displayedColumns: string[] = [ 'primaryIdNumber', 'primaryIdType', 'firstName', 'lastName', 'mobile' ];
  constructor( private __apiService: ApiService,
               public dialog: MatDialog,
               private __routerProvide: Router
              ) { }

  ngOnInit() {
    if (sessionStorage.getItem('sessionKey') === null) {
      this.__routerProvide.navigate(['']);
    }
    this.refresh();
  }

  refresh() {
    this.__apiService.getUnverifiedKYC().subscribe(
      data => {
        this.dataLength = data.data.length;
        this.dataSource = new MatTableDataSource(data.data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error => {
        this.error = error;
      }
    );
  }

  verifyKYC(data) {
    const dialogRef = this.dialog.open(VerifyComponent, {
      width: '80%',
      data: {
        data: data
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.refresh();
      }
    });
  }

  applyFilter(filterValue) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}

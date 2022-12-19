import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyKYCComponent } from './verify-kyc.component';

describe('VerifyKYCComponent', () => {
  let component: VerifyKYCComponent;
  let fixture: ComponentFixture<VerifyKYCComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifyKYCComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyKYCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

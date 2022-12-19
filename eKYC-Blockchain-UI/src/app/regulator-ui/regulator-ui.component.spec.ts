import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegulatorUiComponent } from './regulator-ui.component';

describe('RegulatorUiComponent', () => {
  let component: RegulatorUiComponent;
  let fixture: ComponentFixture<RegulatorUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegulatorUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegulatorUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

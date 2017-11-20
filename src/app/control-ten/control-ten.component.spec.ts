import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlTenComponent } from './control-ten.component';

describe('ControlTenComponent', () => {
  let component: ControlTenComponent;
  let fixture: ComponentFixture<ControlTenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControlTenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControlTenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

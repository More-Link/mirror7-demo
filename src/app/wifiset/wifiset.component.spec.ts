import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WifisetComponent } from './wifiset.component';

describe('WifisetComponent', () => {
  let component: WifisetComponent;
  let fixture: ComponentFixture<WifisetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WifisetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WifisetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

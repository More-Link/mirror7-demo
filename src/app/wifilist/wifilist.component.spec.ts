import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WifilistComponent } from './wifilist.component';

describe('WifilistComponent', () => {
  let component: WifilistComponent;
  let fixture: ComponentFixture<WifilistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WifilistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WifilistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

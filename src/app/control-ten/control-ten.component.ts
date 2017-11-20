import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-control-ten',
  templateUrl: './control-ten.component.html',
  styleUrls: ['./control-ten.component.css']
})
export class ControlTenComponent {

  private _keyUp;
  @Input()
  set keyUp(val: any) {
    this._keyUp = val;
  }
  private _keyLeft;
  @Input()
  set keyLeft(val: any) {
    this._keyLeft = val;
  }
  private _keyEnter;
  @Input()
  set keyEnter(val: any) {
    this._keyEnter = val;
  }
  private _keyRight;
  @Input()
  set keyRight(val: any) {
    this._keyRight = val;
  }
  private _keyDown;
  @Input()
  set keyDown(val: any) {
    this._keyDown = val;
  }

  constructor() { }

  public doKeyUp() {
    if (this._keyUp) {
      this._keyUp();
    }
  }
  public doKeyLeft() {
    if (this._keyLeft) {
      this._keyLeft();
    }
  }
  public doKeyEnter() {
    if (this._keyEnter) {
      this._keyEnter();
    }
  }
  public doKeyRight() {
    if (this._keyRight) {
      this._keyRight();
    }
  }
  public doKeyDown() {
    if (this._keyDown) {
      this._keyDown();
    }
  }
}

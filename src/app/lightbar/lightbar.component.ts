import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-lightbar',
  templateUrl: './lightbar.component.html',
  styleUrls: ['./lightbar.component.css']
})
export class LightbarComponent implements OnInit {

  private alpha = 10;
  @Output() lightclass = new EventEmitter<string>();

  constructor() { }

  ngOnInit() { }

  private deployClass() {
    return this.lightclass.emit(`alpha-${Math.floor(this.alpha)}`);
  }
  lightset(val) {
    if (val > 10 || val < 5) {
      return;
    }
    this.alpha = val;
    this.deployClass();
  }
  lightup() {
    if (this.alpha >= 10) {
      return;
    }
    this.alpha += 1;
    this.deployClass();
  }
  lightdown() {
    if (this.alpha < 5) {
      return;
    }
    this.alpha -= 1;
    this.deployClass();
  }
}

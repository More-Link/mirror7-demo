import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-bottom-key',
  templateUrl: './bottom-key.component.html',
  styleUrls: ['./bottom-key.component.css']
})
export class BottomKeyComponent implements OnInit {

  @Input() text: string;
  @Input() doClick;
  constructor() { }

  ngOnInit() {
  }

  public do() {
    if (this.doClick) {
      this.doClick();
    }
  }

}

import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-wifiset',
  templateUrl: './wifiset.component.html',
  styleUrls: ['./wifiset.component.css']
})
export class WifisetComponent implements OnInit {

  words: string;
  @Input() selectItem;
  @Output() selectItemChange = new EventEmitter<any>();
  @Output() mode = new EventEmitter<boolean>();

  isConnecting = false;

  constructor() { }

  ngOnInit() {
    this.back = () => {
      this.selectItem = null;
      this.selectItemChange.emit(this.selectItem);
    };
    this.connect = () => {
      if (this.isConnecting) {
        return;
      }
      this.isConnecting = true;
      setTimeout(() => {
        this.selectItem.isConnected = true;
        this.selectItemChange.emit(this.selectItem);
        this.mode.emit(false);
      }, 5000);
    };
  }

  back() { }

  connect() { }

  getWords(val) {
    this.words = val;
  }

}

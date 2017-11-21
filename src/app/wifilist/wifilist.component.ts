import { Component, OnInit, AfterViewInit, Input, Output, EventEmitter } from '@angular/core';

declare const $: any;

@Component({
  selector: 'app-wifilist',
  templateUrl: './wifilist.component.html',
  styleUrls: ['./wifilist.component.css']
})
export class WifilistComponent implements OnInit, AfterViewInit {

  public current = 0;

  @Input() list: any[];
  @Input() selectItem;
  @Output() selectItemChange = new EventEmitter<any>();
  @Output() mode = new EventEmitter<boolean>();

  private scrollItem() {
    const doc = document.getElementById(this.current + '');
    const listDoc = doc.parentElement.parentElement;
    const y = this.current <= 3 ? 0 : (this.current - 3) * 70;
    // listDoc.scrollTo(0, y);
    $(listDoc).scrollTo($(`#${this.current - 3}`), 300);
    // $(`#${this.current - 3}`).scroll();
  }

  constructor() { }

  public keyUp() { }
  public keyDown() { }
  public keyEnter() { }

  public back() {
    this.mode.emit(false);
  }

  ngOnInit() {
    const rawList = this.list.sort((a, b) => {
      return b.power - a.power;
    });
    const list = [ ];
    for (const item of rawList) {
      if (item.isConnected) {
        list.push(item);
      }
    }
    for (const item of rawList) {
      if (!item.isConnected) {
        list.push(item);
      }
    }
    this.list = list;
  }

  ngAfterViewInit() {
    this.keyUp = () => {
      if (this.current !== 0) {
        this.current--;
        this.scrollItem();
      }
    };
    this.keyDown = () => {
      if ((this.current + 1) !== this.list.length) {
        this.current++;
        this.scrollItem();
      }
    };
    let connecting = false;
    this.keyEnter = () => {
      if (connecting) {
        return;
      }
      const selectItem = this.list[this.current];
      this.selectItem = selectItem;
      if (selectItem.isConnected) {
        return;
      }
      for (const index of Object.keys(this.list)) {
        this.list[index].isConnected = false;
      }
      this.selectItemChange.emit(null);
      if (!selectItem.encryt) {
        connecting = true;
        this.list[this.current].connecting = true;
        setTimeout(() => {
          this.list[this.current].connecting = false;
          this.list[this.current].isConnected = true;
          this.selectItemChange.emit(this.list[this.current]);
          this.back();
        }, 3000);
      } else {
        this.selectItemChange.emit(selectItem);
      }
    };
  }
}

import { Component, OnInit, AfterViewInit, Input, Output, EventEmitter } from '@angular/core';

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
    listDoc.scrollTo(0, y);
  }

  constructor() { }

  public keyUp() { }
  public keyDown() { }
  public keyEnter() { }

  public back() {
    this.mode.emit(false);
  }

  ngOnInit() {
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
      if (selectItem.isConnected) {
        return;
      }
      this.selectItemChange.emit(null);
      if (!selectItem.encryt) {
        for (const index of Object.keys(this.list)) {
          this.list[index].isConnected = false;
          if (+index === this.current) {
            connecting = true;
            this.list[index].connecting = true;
            setTimeout(() => {
              this.list[index].connecting = false;
              this.list[index].isConnected = true;
              this.selectItemChange.emit(selectItem);
              this.back();
            }, 5000);
          }
        }
      } else {
        this.selectItemChange.emit(selectItem);
      }
    };
  }
}

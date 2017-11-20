import { Component, OnInit } from '@angular/core';

declare const faker: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public wifiContent;
  public wifiList = [ ];
  public wifiMode = false;
  public powerStatus = false;

  ngOnInit() {
    for (let i = 0; i < 50; i++) {
      this.wifiList.push({
        ssid: faker.name.findName(),
        isConnected: false,
        power: +`${(Math.random() * 100)}`.replace(/\.\d+/, ''),
        encryt: faker.random.boolean()
      });
    }
    this.wiifChange = (val) => {
      this.wifiMode = val;
    };
  }

  wiifChange(val) { }
  open() {
    this.powerStatus = true;
  }
  close() {
    this.powerStatus = false;
  }
}

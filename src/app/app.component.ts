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
  private alpha = 10;
  public alphaClass = `alpha-10`;

  ngOnInit() {
    this.wifiList.push({
      ssid: '台山市捷达电器有限公司',
      isConnected: false,
      power: 100,
      encryt: true
    });
    this.wifiList.push({
      ssid: 'More-Link Technology',
      isConnected: false,
      power: 99,
      encryt: true
    });
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

  lightchange(val: string) {
    this.alphaClass = val;
  }

}

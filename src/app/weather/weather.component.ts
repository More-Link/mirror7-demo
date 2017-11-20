import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  @Input() selectItem;
  @Output() mode = new EventEmitter<boolean>();
  curTemp = Math.floor(Math.random() * 10) + 10;
  remoteWeather;
  remoteFutureWeather;
  disMode = 0;
  curDate = Date.now();

  constructor(private http: Http) { }

  ngOnInit() {
    setInterval(() => {
      this.disMode++;
      if (this.disMode >= 2) {
        this.disMode = 0;
      }
    }, 3000);
    setInterval(() => {
      this.curDate = Date.now();
    }, 1000);
    if (this.selectItem) {
      this.http.get('//weixin.jirengu.com/weather').subscribe((val) => {
        const remoteWeather = val.json().weather[0];
        this.remoteWeather = remoteWeather;
        this.remoteFutureWeather = remoteWeather.future.slice(1, 4);
      });
    }
  }

  getWeatherIconClass(val) {
    if (val === '阴') {
      return 'icon-yin';
    } else if (val === '晴') {
      return 'icon-qing';
    } else if (val === '多云') {
      return 'icon-duoyun';
    } else if (val.indexOf('雨') !== -1) {
      return 'icon-yu';
    }
    return '';
  }

  wifi() {
    this.mode.emit(true);
  }

}

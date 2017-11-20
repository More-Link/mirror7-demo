import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WifilistComponent } from './wifilist/wifilist.component';
import { WifisetComponent } from './wifiset/wifiset.component';
import { ControlTenComponent } from './control-ten/control-ten.component';
import { WeatherComponent } from './weather/weather.component';
import { KeyboardComponent } from './keyboard/keyboard.component';
import { BottomKeyComponent } from './bottom-key/bottom-key.component';

@NgModule({
  declarations: [
    AppComponent,
    WifilistComponent,
    WifisetComponent,
    ControlTenComponent,
    WeatherComponent,
    KeyboardComponent,
    BottomKeyComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    console.log('Copyright 2017 More-link');
  }
}

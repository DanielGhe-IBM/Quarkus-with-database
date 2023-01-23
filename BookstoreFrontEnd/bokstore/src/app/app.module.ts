import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddPublishingHouseComponent } from './components/add-publishing-house/add-publishing-house.component'
import { PublishingHouseDetailsComponent } from './components/publishing-house-details/publishing-house-details.component';
import { PublishingHousesListComponent } from './components/publishing-houses-list/publishing-houses-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddPublishingHouseComponent,
    PublishingHouseDetailsComponent,
    PublishingHousesListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

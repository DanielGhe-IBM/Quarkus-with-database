import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublishingHousesListComponent } from './components/publishing-houses-list/publishing-houses-list.component';
import { PublishingHouseDetailsComponent } from './components/publishing-house-details/publishing-house-details.component';
import { AddPublishingHouseComponent } from './components/add-publishing-house/add-publishing-house.component';

const routes: Routes = [
  { path: '', redirectTo: 'publishing-houses', pathMatch: 'full' },
  { path: 'publishing-houses', component: PublishingHousesListComponent },
  { path: 'publishing-houses/:id', component: PublishingHouseDetailsComponent },
  { path: 'add', component: AddPublishingHouseComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
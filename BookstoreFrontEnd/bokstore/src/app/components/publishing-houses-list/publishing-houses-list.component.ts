import { Component, OnInit } from '@angular/core';
import { PublishingHouse } from 'src/app/models/publishing-house.model';
import { PublishingHouseService } from 'src/app/services/publishing-house.service';

@Component({
  selector: 'app-publishing-houses-list',
  templateUrl: './publishing-houses-list.component.html',
  styleUrls: ['./publishing-houses-list.component.css']
})
export class PublishingHousesListComponent implements OnInit {

  publishingHouses?: PublishingHouse[];
  currentPublishingHouse: PublishingHouse = {};
  currentIndex = -1;
  title = '';

  constructor(private publishingHouseService: PublishingHouseService) { }

  ngOnInit(): void {
    this.retrievePublishingHouses();
  }

  retrievePublishingHouses(): void {
    this.publishingHouseService.getAll()
      .subscribe({
        next: (data) => {
          this.publishingHouses = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrievePublishingHouses();
    this.currentPublishingHouse = {};
    this.currentIndex = -1;
  }

  setActivePublishingHouse(publishingHouse: PublishingHouse, index: number): void {
    this.currentPublishingHouse = publishingHouse;
    this.currentIndex = index;
  }


}
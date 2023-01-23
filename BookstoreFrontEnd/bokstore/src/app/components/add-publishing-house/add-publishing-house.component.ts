import { Component } from '@angular/core';
import { PublishingHouse } from 'src/app/models/publishing-house.model';
import { PublishingHouseService } from 'src/app/services/publishing-house.service';

@Component({
  selector: 'app-add-publishing-house',
  templateUrl: './add-publishing-house.component.html',
  styleUrls: ['./add-publishing-house.component.css']
})
export class AddPublishingHouseComponent {

  publishingHouse: PublishingHouse = {
    name: '',
    description: '',
    foundingYear: 0
  };
  submitted = false;

  constructor(private publishingHouseService: PublishingHouseService) { }

  savePublishingHouse(): void {
    const data = {
      name: this.publishingHouse.name,
      description: this.publishingHouse.description,
      foundingYear: this.publishingHouse.foundingYear
    };

    this.publishingHouseService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newPublishingHouse(): void {
    this.submitted = false;
    this.publishingHouse = {
      name: '',
      description: '',
      foundingYear: 0
    };
  }

}
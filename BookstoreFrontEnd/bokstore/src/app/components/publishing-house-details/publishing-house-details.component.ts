import { Component, Input, OnInit } from '@angular/core';
import { PublishingHouseService } from 'src/app/services/publishing-house.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PublishingHouse } from 'src/app/models/publishing-house.model';

@Component({
  selector: 'app-publishing-house-details',
  templateUrl: './publishing-house-details.component.html',
  styleUrls: ['./publishing-house-details.component.css']
})
export class PublishingHouseDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentPublishingHouse: PublishingHouse = {
    name: '',
    description: '',
    foundingYear: 0
  };
  
  message = '';

  constructor(
    private publishingHouseService: PublishingHouseService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getPublishingHouse(this.route.snapshot.params["id"]);
    }
  }

  getPublishingHouse(id: string): void {
    this.publishingHouseService.get(id)
      .subscribe({
        next: (data) => {
          this.currentPublishingHouse = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }


  updatePublishingHouse(): void {
    this.message = '';

    this.publishingHouseService.update(this.currentPublishingHouse.id, this.currentPublishingHouse)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This publishingHouse was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deletePublishingHouse(): void {
    this.publishingHouseService.delete(this.currentPublishingHouse.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/publishing-houses']);
        },
        error: (e) => console.error(e)
      });
  }

}
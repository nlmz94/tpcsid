import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  thumbTitle = '';

  constructor() { }

  ngOnInit(): void {
  }

}

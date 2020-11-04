import {Component, Input, OnInit} from '@angular/core';
import {Featurette} from './featurette';

@Component({
  selector: 'app-featurette',
  templateUrl: './featurette.component.html',
  styleUrls: ['./featurette.component.css']
})
export class FeaturetteComponent implements OnInit {
  @Input() featurette: Featurette;
  @Input() isAtLeft: boolean;
  constructor() { }
  ngOnInit(): void {}
}

import { Component, OnInit } from '@angular/core';
import {Featurette} from '../featurette/featurette';

@Component({
  selector: 'app-feature-component',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.css']
})
export class FeatureComponent implements OnInit {
  constructor() { }
  ngOnInit(): void {}
  generateFeature(): Array<Featurette> {
    const ar = [];
    ar.push(new Featurette('Premiere featurette', 'loremIpsum11111111111111111111111111111111111'));
    ar.push(new Featurette('Deuxieme featurette', 'loremIpsum22222222222222222222222222222222222'))
    return ar;
  }
}

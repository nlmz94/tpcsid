import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  constructor(private userService: UserService, private router: Router) {
    this.registerForm = new FormGroup({
      firstname: new FormControl('', [Validators.required]),
      lastname: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      address: new FormControl('', [Validators.maxLength(200), Validators.minLength(10)]),
      password: new FormControl('', [Validators.required]),
      }); }
  ngOnInit(): void {}
  registerUser(): void {
    this.userService.register(this.registerForm.value).subscribe((returnedId) => {
      this.router.navigate(['user/edit/' + returnedId]);
    },  (error) => { return; } );
    if (this.registerForm.valid) { this.registerForm.reset(); }
  }
}

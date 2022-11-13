import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserCustom } from '../user-custom.model';

@Component({
  selector: 'jhi-user-custom-detail',
  templateUrl: './user-custom-detail.component.html',
})
export class UserCustomDetailComponent implements OnInit {
  userCustom: IUserCustom | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userCustom }) => {
      this.userCustom = userCustom;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.none.service';
import { UserCustomComponent } from '../list/user-custom.component';
import { UserCustomDetailComponent } from '../detail/user-custom-detail.component';
import { UserCustomRoutingResolveService } from './user-custom-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const userCustomRoute: Routes = [
  {
    path: '',
    component: UserCustomComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserCustomDetailComponent,
    resolve: {
      userCustom: UserCustomRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userCustomRoute)],
  exports: [RouterModule],
})
export class UserCustomRoutingModule {}

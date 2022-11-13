import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { UserCustomComponent } from './list/user-custom.component';
import { UserCustomDetailComponent } from './detail/user-custom-detail.component';
import { UserCustomRoutingModule } from './route/user-custom-routing.module';

@NgModule({
  imports: [SharedModule, UserCustomRoutingModule],
  declarations: [UserCustomComponent, UserCustomDetailComponent],
})
export class UserCustomModule {}

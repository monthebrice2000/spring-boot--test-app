import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserCustom } from '../user-custom.model';
import { UserCustomService } from '../service/user-custom.service';

@Injectable({ providedIn: 'root' })
export class UserCustomRoutingResolveService implements Resolve<IUserCustom | null> {
  constructor(protected service: UserCustomService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserCustom | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userCustom: HttpResponse<IUserCustom>) => {
          if (userCustom.body) {
            return of(userCustom.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}

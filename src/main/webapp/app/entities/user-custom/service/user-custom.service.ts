import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.none.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserCustom, NewUserCustom } from '../user-custom.model';

export type PartialUpdateUserCustom = Partial<IUserCustom> & Pick<IUserCustom, 'id'>;

type RestOf<T extends IUserCustom | NewUserCustom> = Omit<T, 'joinDate' | 'lastLoginDate' | 'lastLoginDateDisplay'> & {
  joinDate?: string | null;
  lastLoginDate?: string | null;
  lastLoginDateDisplay?: string | null;
};

export type RestUserCustom = RestOf<IUserCustom>;

export type NewRestUserCustom = RestOf<NewUserCustom>;

export type PartialUpdateRestUserCustom = RestOf<PartialUpdateUserCustom>;

export type EntityResponseType = HttpResponse<IUserCustom>;
export type EntityArrayResponseType = HttpResponse<IUserCustom[]>;

@Injectable({ providedIn: 'root' })
export class UserCustomService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-customs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestUserCustom>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestUserCustom[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  getUserCustomIdentifier(userCustom: Pick<IUserCustom, 'id'>): number {
    return userCustom.id;
  }

  compareUserCustom(o1: Pick<IUserCustom, 'id'> | null, o2: Pick<IUserCustom, 'id'> | null): boolean {
    return o1 && o2 ? this.getUserCustomIdentifier(o1) === this.getUserCustomIdentifier(o2) : o1 === o2;
  }

  addUserCustomToCollectionIfMissing<Type extends Pick<IUserCustom, 'id'>>(
    userCustomCollection: Type[],
    ...userCustomsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userCustoms: Type[] = userCustomsToCheck.filter(isPresent);
    if (userCustoms.length > 0) {
      const userCustomCollectionIdentifiers = userCustomCollection.map(userCustomItem => this.getUserCustomIdentifier(userCustomItem)!);
      const userCustomsToAdd = userCustoms.filter(userCustomItem => {
        const userCustomIdentifier = this.getUserCustomIdentifier(userCustomItem);
        if (userCustomCollectionIdentifiers.includes(userCustomIdentifier)) {
          return false;
        }
        userCustomCollectionIdentifiers.push(userCustomIdentifier);
        return true;
      });
      return [...userCustomsToAdd, ...userCustomCollection];
    }
    return userCustomCollection;
  }

  protected convertDateFromClient<T extends IUserCustom | NewUserCustom | PartialUpdateUserCustom>(userCustom: T): RestOf<T> {
    return {
      ...userCustom,
      joinDate: userCustom.joinDate?.format(DATE_FORMAT) ?? null,
      lastLoginDate: userCustom.lastLoginDate?.format(DATE_FORMAT) ?? null,
      lastLoginDateDisplay: userCustom.lastLoginDateDisplay?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restUserCustom: RestUserCustom): IUserCustom {
    return {
      ...restUserCustom,
      joinDate: restUserCustom.joinDate ? dayjs(restUserCustom.joinDate) : undefined,
      lastLoginDate: restUserCustom.lastLoginDate ? dayjs(restUserCustom.lastLoginDate) : undefined,
      lastLoginDateDisplay: restUserCustom.lastLoginDateDisplay ? dayjs(restUserCustom.lastLoginDateDisplay) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestUserCustom>): HttpResponse<IUserCustom> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestUserCustom[]>): HttpResponse<IUserCustom[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}

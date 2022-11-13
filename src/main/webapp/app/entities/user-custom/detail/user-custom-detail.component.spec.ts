import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserCustomDetailComponent } from './user-custom-detail.component';

describe('UserCustom Management Detail Component', () => {
  let comp: UserCustomDetailComponent;
  let fixture: ComponentFixture<UserCustomDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userCustom: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(UserCustomDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserCustomDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userCustom on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userCustom).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

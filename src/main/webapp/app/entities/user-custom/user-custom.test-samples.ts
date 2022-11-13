import dayjs from 'dayjs/esm';

import { IUserCustom, NewUserCustom } from './user-custom.model';

export const sampleWithRequiredData: IUserCustom = {
  id: 16400,
  firstName: 'Taurean',
  lastName: 'Gulgowski',
  username: 'Cambridgeshire compr',
  email: 'Adella.Stoltenberg@y',
  password: 'Usability Coordinato',
  roles: 'index copying e-services',
  authorities: 'Virtual bus e-services',
  isNotLocked: false,
  isActived: false,
};

export const sampleWithPartialData: IUserCustom = {
  id: 24930,
  firstName: 'Tristin',
  lastName: "O'Connell",
  username: 'utilisation',
  email: 'Eryn41@hotmail.com',
  password: 'open-source',
  roles: 'compressing Accountability',
  authorities: 'scalable',
  isNotLocked: true,
  isActived: true,
  joinDate: dayjs('2022-11-11'),
};

export const sampleWithFullData: IUserCustom = {
  id: 93807,
  firstName: 'Mike',
  lastName: 'Schimmel',
  username: 'knowledge',
  email: 'Franco24@hotmail.com',
  password: 'capacitor',
  roles: 'optical Diverse payment',
  authorities: 'Soap',
  isNotLocked: true,
  isActived: true,
  joinDate: dayjs('2022-11-12'),
  lastLoginDate: dayjs('2022-11-12'),
  lastLoginDateDisplay: dayjs('2022-11-12'),
};

export const sampleWithNewData: NewUserCustom = {
  firstName: 'Buford',
  lastName: 'Johnson',
  username: 'Rustic',
  email: 'Shane.Heaney58@hotma',
  password: 'silver RAM',
  roles: 'Steel program',
  authorities: 'Human Consultant',
  isNotLocked: true,
  isActived: true,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

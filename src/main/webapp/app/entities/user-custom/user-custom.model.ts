import dayjs from 'dayjs/esm';

export interface IUserCustom {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  username?: string | null;
  email?: string | null;
  password?: string | null;
  roles?: string | null;
  authorities?: string | null;
  isNotLocked?: boolean | null;
  isActived?: boolean | null;
  joinDate?: dayjs.Dayjs | null;
  lastLoginDate?: dayjs.Dayjs | null;
  lastLoginDateDisplay?: dayjs.Dayjs | null;
}

export type NewUserCustom = Omit<IUserCustom, 'id'> & { id: null };

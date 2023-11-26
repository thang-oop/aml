export interface IJhiUserAuthority {
  id?: number;
  userId?: string | null;
  authorityName?: string | null;
}

export const defaultValue: Readonly<IJhiUserAuthority> = {};

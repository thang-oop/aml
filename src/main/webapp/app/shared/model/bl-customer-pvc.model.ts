import dayjs from 'dayjs';

export interface IBLCustomerPvc {
  id?: number;
  cif?: string | null;
  fullName?: string | null;
  dateOfBirth?: string | null;
  legalId?: string | null;
  legalType?: string | null;
  branch?: string | null;
  blCustomerId?: string | null;
  nameBl?: string | null;
  dateOfBirthBl?: string | null;
  legalIdTypeBl?: string | null;
  legalIdNumber?: string | null;
  matchAttr?: string | null;
  valueAttr?: string | null;
  weightAttr?: string | null;
  score?: string | null;
  status?: string | null;
  remark?: string | null;
  recordStatus?: string | null;
  coCode?: string | null;
  createdBy?: string | null;
  dateCreated?: string | null;
  authoriseBy?: string | null;
  dateAuthorise?: string | null;
}

export const defaultValue: Readonly<IBLCustomerPvc> = {};

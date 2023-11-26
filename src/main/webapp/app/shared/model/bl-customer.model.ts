import dayjs from 'dayjs';

export interface IBLCustomer {
  id?: number;
  fullName?: string | null;
  firstName?: string | null;
  lastName?: string | null;
  otherName1?: string | null;
  otherName2?: string | null;
  otherName3?: string | null;
  positionBl?: string | null;
  dateOfBirthBl?: string | null;
  countryBl1?: string | null;
  countryBl2?: string | null;
  legalIdTypeBl1?: string | null;
  legalIdNumber1?: string | null;
  legalIdTypeBl2?: string | null;
  legalIdNumber2?: string | null;
  otherInfLegal1?: string | null;
  otherInfLegal2?: string | null;
  addressBl1?: string | null;
  addressBl2?: string | null;
  addressNowBl1?: string | null;
  addressNowBl2?: string | null;
  typeBl?: string | null;
  source?: string | null;
  recordStatus?: string | null;
  uploadFileId?: string | null;
  coCode?: string | null;
  createdBy?: string | null;
  dateCreated?: string | null;
  authoriseBy?: string | null;
  dateAuthorise?: string | null;
}

export const defaultValue: Readonly<IBLCustomer> = {};

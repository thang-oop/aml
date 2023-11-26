export interface IBLParamter {
  id?: number;
  keyId?: string | null;
  keyValue?: string | null;
  recordStatus?: string | null;
  description?: string | null;
  createdBy?: string | null;
  dateCreated?: string | null;
  authoriseBy?: string | null;
  dateauthoriseBy?: string | null;
}

export const defaultValue: Readonly<IBLParamter> = {};

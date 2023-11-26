export interface IBLCondition {
  id?: number;
  name?: string | null;
  description?: string | null;
  blackListFlds?: string | null;
  customerFlds?: string | null;
  weightPoint?: number | null;
  ruleId?: number | null;
  createdBy?: string | null;
  dateCreated?: string | null;

  authoriseBy?: string | null;
  dateauthoriseBy?: string | null;
}

export const defaultValue: Readonly<IBLCondition> = {};

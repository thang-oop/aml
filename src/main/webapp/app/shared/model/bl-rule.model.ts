export interface IBLRule {
  id?: number;
  name?: string | null;
  description?: string | null;
  sourceIds?: string | null;
  customerType?: string | null;
  scoreMinimum?: number | null;
  recordStatus?: string | null;
  createdBy?: string | null;
  dateCreated?: string | null;
  authoriseBy?: string | null;
  dateAuthorise?: string | null;
}

export const defaultValue: Readonly<IBLRule> = {};

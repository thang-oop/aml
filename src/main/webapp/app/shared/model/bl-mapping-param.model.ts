export interface IBLMappingParam {
  id?: number;
  sourceName?: string | null;
  sourceFilePrefix?: string | null;
  sourceCols?: string | null;
  sourceRef?: string | null;
  recordStatus?: string | null;
  createdBy?: string | null;
  dateCreated?: string | null;
  authoriseBy?: string | null;
  dateAuthorise?: string | null;
}

export const defaultValue: Readonly<IBLMappingParam> = {};

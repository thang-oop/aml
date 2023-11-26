import dayjs from 'dayjs';

export interface IBLUploadFile {
  id?: number;
  fileName?: string | null;
  systemFileName?: string | null;
  description?: string | null;
  tagetCompany?: string | null;
  validate?: string | null;
  serviceStatus?: string | null;
  fileSize?: number | null;
  recordStatus?: string | null;
  uploadBy?: string | null;
  dateUpload?: string | null;
  authoriseBy?: string | null;
  dateAuthorise?: string | null;
  createdBy?: string | null;
  dateCreated?: string | null;
}

export const defaultValue: Readonly<IBLUploadFile> = {};

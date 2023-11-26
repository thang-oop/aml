import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLUploadFile, defaultValue } from 'app/shared/model/bl-upload-file.model';

export const ACTION_TYPES = {
  FETCH_BLUPLOADFILE_LIST: 'bLUploadFile/FETCH_BLUPLOADFILE_LIST',
 SOURD_DATA_LIST: 'bLUploadFile/SOURD_DATA_LIST',
  FETCH_BLUPLOADFILE: 'bLUploadFile/FETCH_BLUPLOADFILE',
  CREATE_BLUPLOADFILE: 'bLUploadFile/CREATE_BLUPLOADFILE',
  UPDATE_BLUPLOADFILE: 'bLUploadFile/UPDATE_BLUPLOADFILE',
  PARTIAL_UPDATE_BLUPLOADFILE: 'bLUploadFile/PARTIAL_UPDATE_BLUPLOADFILE',
  DELETE_BLUPLOADFILE: 'bLUploadFile/DELETE_BLUPLOADFILE',
  RESET: 'bLUploadFile/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLUploadFile>,
  entity: defaultValue,
  listSourceData: [],
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BLUploadFileState = Readonly<typeof initialState>;

// Reducer

export default (state: BLUploadFileState = initialState, action): BLUploadFileState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLUPLOADFILE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLUPLOADFILE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLUPLOADFILE):
    case REQUEST(ACTION_TYPES.UPDATE_BLUPLOADFILE):
    case REQUEST(ACTION_TYPES.DELETE_BLUPLOADFILE):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLUPLOADFILE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLUPLOADFILE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLUPLOADFILE):
    case FAILURE(ACTION_TYPES.CREATE_BLUPLOADFILE):
    case FAILURE(ACTION_TYPES.UPDATE_BLUPLOADFILE):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLUPLOADFILE):
    case FAILURE(ACTION_TYPES.DELETE_BLUPLOADFILE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
      case SUCCESS(ACTION_TYPES.SOURD_DATA_LIST):
        return {
          ...state,
          loading: false,
          listSourceData: action.payload.data,
        };
    case SUCCESS(ACTION_TYPES.FETCH_BLUPLOADFILE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLUPLOADFILE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLUPLOADFILE):
    case SUCCESS(ACTION_TYPES.UPDATE_BLUPLOADFILE):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLUPLOADFILE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLUPLOADFILE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/bl-upload-files';

// Actions

export const getEntities: ICrudGetAllAction<IBLUploadFile> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BLUPLOADFILE_LIST,
    payload: axios.get<IBLUploadFile>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBLUploadFile> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLUPLOADFILE,
    payload: axios.get<IBLUploadFile>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLUploadFile> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLUPLOADFILE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLUploadFile> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLUPLOADFILE,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLUploadFile> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLUPLOADFILE,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLUploadFile> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLUPLOADFILE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});



export const getListSourceData = () => {
  const requestUrl = 'api/bl-mapping-params';
  return {
    type: ACTION_TYPES.SOURD_DATA_LIST,
    payload: axios.get<IBLUploadFile>(`${requestUrl}`),
  };
};


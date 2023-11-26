import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLMappingParam, defaultValue } from 'app/shared/model/bl-mapping-param.model';
import { IBLParamter } from 'app/shared/model/bl-paramter.model';

export const ACTION_TYPES = {
  FETCH_BLMAPPINGPARAM_LIST: 'bLMappingParam/FETCH_BLMAPPINGPARAM_LIST',
  FETCH_BLMAPPINGPARAM: 'bLMappingParam/FETCH_BLMAPPINGPARAM',
  CREATE_BLMAPPINGPARAM: 'bLMappingParam/CREATE_BLMAPPINGPARAM',
  UPDATE_BLMAPPINGPARAM: 'bLMappingParam/UPDATE_BLMAPPINGPARAM',
  PARTIAL_UPDATE_BLMAPPINGPARAM: 'bLMappingParam/PARTIAL_UPDATE_BLMAPPINGPARAM',
  DELETE_BLMAPPINGPARAM: 'bLMappingParam/DELETE_BLMAPPINGPARAM',
  RESET: 'bLMappingParam/RESET',
  FETCH_BLPARAMTER: 'bLParamter/FETCH_BLPARAMTER',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLMappingParam>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BLMappingParamState = Readonly<typeof initialState>;

// Reducer

export default (state: BLMappingParamState = initialState, action): BLMappingParamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLMAPPINGPARAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLMAPPINGPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLMAPPINGPARAM):
    case REQUEST(ACTION_TYPES.UPDATE_BLMAPPINGPARAM):
    case REQUEST(ACTION_TYPES.DELETE_BLMAPPINGPARAM):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLMAPPINGPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLMAPPINGPARAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLMAPPINGPARAM):
    case FAILURE(ACTION_TYPES.CREATE_BLMAPPINGPARAM):
    case FAILURE(ACTION_TYPES.UPDATE_BLMAPPINGPARAM):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLMAPPINGPARAM):
    case FAILURE(ACTION_TYPES.DELETE_BLMAPPINGPARAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLMAPPINGPARAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLMAPPINGPARAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLMAPPINGPARAM):
    case SUCCESS(ACTION_TYPES.UPDATE_BLMAPPINGPARAM):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLMAPPINGPARAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLMAPPINGPARAM):
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

const apiUrl = 'api/bl-mapping-params';
const apiUrlParams = 'api/bl-paramters-key';

// Actions

export const getEntities: ICrudGetAllAction<IBLMappingParam> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BLMAPPINGPARAM_LIST,
    payload: axios.get<IBLMappingParam>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBLMappingParam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLMAPPINGPARAM,
    payload: axios.get<IBLMappingParam>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLMappingParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLMAPPINGPARAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLMappingParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLMAPPINGPARAM,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLMappingParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLMAPPINGPARAM,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLMappingParam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLMAPPINGPARAM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getListSourceData: ICrudGetAllAction<IBLMappingParam> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BLMAPPINGPARAM_LIST,
    payload: axios.get<IBLMappingParam>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getSourceDataEntity: ICrudGetAction<IBLMappingParam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLMAPPINGPARAM,
    payload: axios.get<IBLMappingParam>(requestUrl),
  };
};

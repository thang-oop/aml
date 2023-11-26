import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLParamter, defaultValue } from 'app/shared/model/bl-paramter.model';

export const ACTION_TYPES = {
  FETCH_BLPARAMTER_LIST: 'bLParamter/FETCH_BLPARAMTER_LIST',
  FETCH_BLPARAMTER: 'bLParamter/FETCH_BLPARAMTER',
  CREATE_BLPARAMTER: 'bLParamter/CREATE_BLPARAMTER',
  UPDATE_BLPARAMTER: 'bLParamter/UPDATE_BLPARAMTER',
  PARTIAL_UPDATE_BLPARAMTER: 'bLParamter/PARTIAL_UPDATE_BLPARAMTER',
  DELETE_BLPARAMTER: 'bLParamter/DELETE_BLPARAMTER',
  RESET: 'bLParamter/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLParamter>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BLParamterState = Readonly<typeof initialState>;

// Reducer

export default (state: BLParamterState = initialState, action): BLParamterState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLPARAMTER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLPARAMTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLPARAMTER):
    case REQUEST(ACTION_TYPES.UPDATE_BLPARAMTER):
    case REQUEST(ACTION_TYPES.DELETE_BLPARAMTER):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLPARAMTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLPARAMTER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLPARAMTER):
    case FAILURE(ACTION_TYPES.CREATE_BLPARAMTER):
    case FAILURE(ACTION_TYPES.UPDATE_BLPARAMTER):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLPARAMTER):
    case FAILURE(ACTION_TYPES.DELETE_BLPARAMTER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLPARAMTER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLPARAMTER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLPARAMTER):
    case SUCCESS(ACTION_TYPES.UPDATE_BLPARAMTER):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLPARAMTER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLPARAMTER):
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

const apiUrl = 'api/bl-paramters';

// Actions

export const getEntities: ICrudGetAllAction<IBLParamter> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BLPARAMTER_LIST,
    payload: axios.get<IBLParamter>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBLParamter> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLPARAMTER,
    payload: axios.get<IBLParamter>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLParamter> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLPARAMTER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLParamter> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLPARAMTER,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLParamter> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLPARAMTER,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLParamter> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLPARAMTER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getParamterByKey: ICrudGetAction<IBLParamter> = key_id => {
  const requestUrl = `${apiUrl}/keyid/${key_id}`;
  return {
    type: ACTION_TYPES.FETCH_BLPARAMTER,
    payload: axios.get<IBLParamter>(requestUrl),
  };
};

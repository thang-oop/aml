import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLCustomerPvc, defaultValue } from 'app/shared/model/bl-customer-pvc.model';

export const ACTION_TYPES = {
  FETCH_BLCUSTOMERPVC_LIST: 'bLCustomerPvc/FETCH_BLCUSTOMERPVC_LIST',
  FETCH_BLCUSTOMERPVC: 'bLCustomerPvc/FETCH_BLCUSTOMERPVC',
  CREATE_BLCUSTOMERPVC: 'bLCustomerPvc/CREATE_BLCUSTOMERPVC',
  UPDATE_BLCUSTOMERPVC: 'bLCustomerPvc/UPDATE_BLCUSTOMERPVC',
  PARTIAL_UPDATE_BLCUSTOMERPVC: 'bLCustomerPvc/PARTIAL_UPDATE_BLCUSTOMERPVC',
  DELETE_BLCUSTOMERPVC: 'bLCustomerPvc/DELETE_BLCUSTOMERPVC',
  RESET: 'bLCustomerPvc/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLCustomerPvc>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BLCustomerPvcState = Readonly<typeof initialState>;

// Reducer

export default (state: BLCustomerPvcState = initialState, action): BLCustomerPvcState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLCUSTOMERPVC_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLCUSTOMERPVC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLCUSTOMERPVC):
    case REQUEST(ACTION_TYPES.UPDATE_BLCUSTOMERPVC):
    case REQUEST(ACTION_TYPES.DELETE_BLCUSTOMERPVC):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMERPVC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLCUSTOMERPVC_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLCUSTOMERPVC):
    case FAILURE(ACTION_TYPES.CREATE_BLCUSTOMERPVC):
    case FAILURE(ACTION_TYPES.UPDATE_BLCUSTOMERPVC):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMERPVC):
    case FAILURE(ACTION_TYPES.DELETE_BLCUSTOMERPVC):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLCUSTOMERPVC_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLCUSTOMERPVC):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLCUSTOMERPVC):
    case SUCCESS(ACTION_TYPES.UPDATE_BLCUSTOMERPVC):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMERPVC):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLCUSTOMERPVC):
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

const apiUrl = 'api/bl-customer-pvcs';

// Actions

export const getEntities: ICrudGetAllAction<IBLCustomerPvc> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BLCUSTOMERPVC_LIST,
    payload: axios.get<IBLCustomerPvc>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBLCustomerPvc> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLCUSTOMERPVC,
    payload: axios.get<IBLCustomerPvc>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLCustomerPvc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLCUSTOMERPVC,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLCustomerPvc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLCUSTOMERPVC,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLCustomerPvc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMERPVC,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLCustomerPvc> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLCUSTOMERPVC,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

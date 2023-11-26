import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLCustomer, defaultValue } from 'app/shared/model/bl-customer.model';

export const ACTION_TYPES = {
  FETCH_BLCUSTOMER_LIST: 'bLCustomer/FETCH_BLCUSTOMER_LIST',
  FETCH_BLCUSTOMER: 'bLCustomer/FETCH_BLCUSTOMER',
  CREATE_BLCUSTOMER: 'bLCustomer/CREATE_BLCUSTOMER',
  UPDATE_BLCUSTOMER: 'bLCustomer/UPDATE_BLCUSTOMER',
  PARTIAL_UPDATE_BLCUSTOMER: 'bLCustomer/PARTIAL_UPDATE_BLCUSTOMER',
  DELETE_BLCUSTOMER: 'bLCustomer/DELETE_BLCUSTOMER',
  RESET: 'bLCustomer/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLCustomer>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BLCustomerState = Readonly<typeof initialState>;

// Reducer

export default (state: BLCustomerState = initialState, action): BLCustomerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLCUSTOMER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLCUSTOMER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLCUSTOMER):
    case REQUEST(ACTION_TYPES.UPDATE_BLCUSTOMER):
    case REQUEST(ACTION_TYPES.DELETE_BLCUSTOMER):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLCUSTOMER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLCUSTOMER):
    case FAILURE(ACTION_TYPES.CREATE_BLCUSTOMER):
    case FAILURE(ACTION_TYPES.UPDATE_BLCUSTOMER):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMER):
    case FAILURE(ACTION_TYPES.DELETE_BLCUSTOMER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLCUSTOMER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLCUSTOMER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLCUSTOMER):
    case SUCCESS(ACTION_TYPES.UPDATE_BLCUSTOMER):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLCUSTOMER):
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

const apiUrl = 'api/bl-customers';

// Actions

export const getEntities: ICrudGetAllAction<IBLCustomer> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BLCUSTOMER_LIST,
    payload: axios.get<IBLCustomer>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBLCustomer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLCUSTOMER,
    payload: axios.get<IBLCustomer>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLCustomer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLCUSTOMER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLCustomer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLCUSTOMER,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLCustomer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLCUSTOMER,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLCustomer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLCUSTOMER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

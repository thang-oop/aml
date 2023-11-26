import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLCondition, defaultValue } from 'app/shared/model/bl-condition.model';

export const ACTION_TYPES = {
  FETCH_BLCONDITION_LIST: 'bLCondition/FETCH_BLCONDITION_LIST',
  FETCH_BLCONDITION: 'bLCondition/FETCH_BLCONDITION',
  CREATE_BLCONDITION: 'bLCondition/CREATE_BLCONDITION',
  UPDATE_BLCONDITION: 'bLCondition/UPDATE_BLCONDITION',
  PARTIAL_UPDATE_BLCONDITION: 'bLCondition/PARTIAL_UPDATE_BLCONDITION',
  DELETE_BLCONDITION: 'bLCondition/DELETE_BLCONDITION',
  RESET: 'bLCondition/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLCondition>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type BLConditionState = Readonly<typeof initialState>;

// Reducer

export default (state: BLConditionState = initialState, action): BLConditionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLCONDITION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLCONDITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLCONDITION):
    case REQUEST(ACTION_TYPES.UPDATE_BLCONDITION):
    case REQUEST(ACTION_TYPES.DELETE_BLCONDITION):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLCONDITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLCONDITION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLCONDITION):
    case FAILURE(ACTION_TYPES.CREATE_BLCONDITION):
    case FAILURE(ACTION_TYPES.UPDATE_BLCONDITION):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLCONDITION):
    case FAILURE(ACTION_TYPES.DELETE_BLCONDITION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLCONDITION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLCONDITION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLCONDITION):
    case SUCCESS(ACTION_TYPES.UPDATE_BLCONDITION):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLCONDITION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLCONDITION):
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

const apiUrl = 'api/bl-conditions';

// Actions

export const getEntities: ICrudGetAllAction<IBLCondition> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BLCONDITION_LIST,
  payload: axios.get<IBLCondition>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IBLCondition> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLCONDITION,
    payload: axios.get<IBLCondition>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLCondition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLCONDITION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLCondition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLCONDITION,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLCondition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLCONDITION,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLCondition> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLCONDITION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

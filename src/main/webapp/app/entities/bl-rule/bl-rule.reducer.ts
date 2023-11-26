import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBLRule, defaultValue } from 'app/shared/model/bl-rule.model';

export const ACTION_TYPES = {
  FETCH_BLRULE_LIST: 'bLRule/FETCH_BLRULE_LIST',
  FETCH_BLRULE: 'bLRule/FETCH_BLRULE',
  CREATE_BLRULE: 'bLRule/CREATE_BLRULE',
  UPDATE_BLRULE: 'bLRule/UPDATE_BLRULE',
  PARTIAL_UPDATE_BLRULE: 'bLRule/PARTIAL_UPDATE_BLRULE',
  DELETE_BLRULE: 'bLRule/DELETE_BLRULE',
  RESET: 'bLRule/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBLRule>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type BLRuleState = Readonly<typeof initialState>;

// Reducer

export default (state: BLRuleState = initialState, action): BLRuleState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLRULE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLRULE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BLRULE):
    case REQUEST(ACTION_TYPES.UPDATE_BLRULE):
    case REQUEST(ACTION_TYPES.DELETE_BLRULE):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BLRULE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BLRULE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLRULE):
    case FAILURE(ACTION_TYPES.CREATE_BLRULE):
    case FAILURE(ACTION_TYPES.UPDATE_BLRULE):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BLRULE):
    case FAILURE(ACTION_TYPES.DELETE_BLRULE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLRULE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLRULE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLRULE):
    case SUCCESS(ACTION_TYPES.UPDATE_BLRULE):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BLRULE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLRULE):
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

const apiUrl = 'api/bl-rules';

// Actions

export const getEntities: ICrudGetAllAction<IBLRule> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BLRULE_LIST,
  payload: axios.get<IBLRule>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IBLRule> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLRULE,
    payload: axios.get<IBLRule>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBLRule> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLRULE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBLRule> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLRULE,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBLRule> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BLRULE,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBLRule> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLRULE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getRuleEntity: ICrudGetAction<IBLRule> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLRULE,
    payload: axios.get<IBLRule>(requestUrl),
  };
};

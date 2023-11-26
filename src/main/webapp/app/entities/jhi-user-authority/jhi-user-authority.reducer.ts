import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IJhiUserAuthority, defaultValue } from 'app/shared/model/jhi-user-authority.model';

export const ACTION_TYPES = {
  FETCH_JHIUSERAUTHORITY_LIST: 'jhiUserAuthority/FETCH_JHIUSERAUTHORITY_LIST',
  FETCH_JHIUSERAUTHORITY: 'jhiUserAuthority/FETCH_JHIUSERAUTHORITY',
  CREATE_JHIUSERAUTHORITY: 'jhiUserAuthority/CREATE_JHIUSERAUTHORITY',
  UPDATE_JHIUSERAUTHORITY: 'jhiUserAuthority/UPDATE_JHIUSERAUTHORITY',
  PARTIAL_UPDATE_JHIUSERAUTHORITY: 'jhiUserAuthority/PARTIAL_UPDATE_JHIUSERAUTHORITY',
  DELETE_JHIUSERAUTHORITY: 'jhiUserAuthority/DELETE_JHIUSERAUTHORITY',
  RESET: 'jhiUserAuthority/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IJhiUserAuthority>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type JhiUserAuthorityState = Readonly<typeof initialState>;

// Reducer

export default (state: JhiUserAuthorityState = initialState, action): JhiUserAuthorityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_JHIUSERAUTHORITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_JHIUSERAUTHORITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_JHIUSERAUTHORITY):
    case REQUEST(ACTION_TYPES.UPDATE_JHIUSERAUTHORITY):
    case REQUEST(ACTION_TYPES.DELETE_JHIUSERAUTHORITY):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_JHIUSERAUTHORITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_JHIUSERAUTHORITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_JHIUSERAUTHORITY):
    case FAILURE(ACTION_TYPES.CREATE_JHIUSERAUTHORITY):
    case FAILURE(ACTION_TYPES.UPDATE_JHIUSERAUTHORITY):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_JHIUSERAUTHORITY):
    case FAILURE(ACTION_TYPES.DELETE_JHIUSERAUTHORITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_JHIUSERAUTHORITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_JHIUSERAUTHORITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_JHIUSERAUTHORITY):
    case SUCCESS(ACTION_TYPES.UPDATE_JHIUSERAUTHORITY):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_JHIUSERAUTHORITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_JHIUSERAUTHORITY):
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

const apiUrl = 'api/jhi-user-authorities';

// Actions

export const getEntities: ICrudGetAllAction<IJhiUserAuthority> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_JHIUSERAUTHORITY_LIST,
    payload: axios.get<IJhiUserAuthority>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IJhiUserAuthority> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_JHIUSERAUTHORITY,
    payload: axios.get<IJhiUserAuthority>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IJhiUserAuthority> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_JHIUSERAUTHORITY,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IJhiUserAuthority> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_JHIUSERAUTHORITY,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IJhiUserAuthority> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_JHIUSERAUTHORITY,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IJhiUserAuthority> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_JHIUSERAUTHORITY,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

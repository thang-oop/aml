import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from './user-management';
// prettier-ignore
import bLParamter, {
  BLParamterState
} from 'app/entities/bl-paramter/bl-paramter.reducer';
// prettier-ignore
import bLUploadFile, {
  BLUploadFileState
} from 'app/entities/bl-upload-file/bl-upload-file.reducer';
// prettier-ignore
import bLCustomer, {
  BLCustomerState
} from 'app/entities/bl-customer/bl-customer.reducer';
// prettier-ignore
import bLCustomerPvc, {
  BLCustomerPvcState
} from 'app/entities/bl-customer-pvc/bl-customer-pvc.reducer';
// prettier-ignore
import jhiUserAuthority, {
  JhiUserAuthorityState
} from 'app/entities/jhi-user-authority/jhi-user-authority.reducer';
// prettier-ignore
import bLMappingParam, {
  BLMappingParamState
} from 'app/entities/bl-mapping-param/bl-mapping-param.reducer';
// prettier-ignore
import bLRule, {
  BLRuleState
} from 'app/entities/bl-rule/bl-rule.reducer';
// prettier-ignore
import bLCondition, {
  BLConditionState
} from 'app/entities/bl-condition/bl-condition.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly bLParamter: BLParamterState;
  readonly bLUploadFile: BLUploadFileState;
  readonly bLCustomer: BLCustomerState;
  readonly bLCustomerPvc: BLCustomerPvcState;
  readonly jhiUserAuthority: JhiUserAuthorityState;
  readonly bLMappingParam: BLMappingParamState;
  readonly bLRule: BLRuleState;
  readonly bLCondition: BLConditionState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  bLParamter,
  bLUploadFile,
  bLCustomer,
  bLCustomerPvc,
  jhiUserAuthority,
  bLMappingParam,
  bLRule,
  bLCondition,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;

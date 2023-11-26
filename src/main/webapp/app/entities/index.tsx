import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLParamter from './bl-paramter';
import BLUploadFile from './bl-upload-file';
import BLCustomer from './bl-customer';
import BLCustomerPvc from './bl-customer-pvc';
import JhiUserAuthority from './jhi-user-authority';
import BLMappingParamList from './bl-mapping-param';
import BLRule from './bl-rule';
import BLCondition from './bl-condition';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}bl-paramter`} component={BLParamter} />
      <ErrorBoundaryRoute path={`${match.url}bl-upload-file`} component={BLUploadFile} />
      <ErrorBoundaryRoute path={`${match.url}bl-customer`} component={BLCustomer} />
      <ErrorBoundaryRoute path={`${match.url}bl-customer-pvc`} component={BLCustomerPvc} />
      <ErrorBoundaryRoute path={`${match.url}jhi-user-authority`} component={JhiUserAuthority} />
      <ErrorBoundaryRoute path={`${match.url}bl-mapping-param`} component={BLMappingParamList} />
      <ErrorBoundaryRoute path={`${match.url}bl-rule`} component={BLRule} />
      <ErrorBoundaryRoute path={`${match.url}bl-condition`} component={BLCondition} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;

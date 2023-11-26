import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLCustomerPvc from './bl-customer-pvc';
import BLCustomerPvcDetail from './bl-customer-pvc-detail';
import BLCustomerPvcUpdate from './bl-customer-pvc-update';
import BLCustomerPvcDeleteDialog from './bl-customer-pvc-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLCustomerPvcUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLCustomerPvcUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLCustomerPvcDetail} />
      <ErrorBoundaryRoute path={match.url} component={BLCustomerPvc} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLCustomerPvcDeleteDialog} />
  </>
);

export default Routes;

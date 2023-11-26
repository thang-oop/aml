import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLCustomer from './bl-customer';
import BLCustomerDetail from './bl-customer-detail';
import BLCustomerUpdate from './bl-customer-update';
import BLCustomerDeleteDialog from './bl-customer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLCustomerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLCustomerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLCustomerDetail} />
      <ErrorBoundaryRoute path={match.url} component={BLCustomer} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLCustomerDeleteDialog} />
  </>
);

export default Routes;

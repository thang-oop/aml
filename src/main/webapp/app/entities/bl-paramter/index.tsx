import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLParamter from './bl-paramter';
import BLParamterDetail from './bl-paramter-detail';
import BLParamterUpdate from './bl-paramter-update';
import BLParamterDeleteDialog from './bl-paramter-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLParamterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLParamterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLParamterDetail} />
      <ErrorBoundaryRoute path={match.url} component={BLParamter} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLParamterDeleteDialog} />
  </>
);

export default Routes;

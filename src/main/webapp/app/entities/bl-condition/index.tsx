import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLCondition from './bl-condition';
import BLConditionDetail from './bl-condition-detail';
import BLConditionUpdate from './bl-condition-update';
import BLConditionDeleteDialog from './bl-condition-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLConditionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLConditionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLConditionDetail} />
      <ErrorBoundaryRoute path={match.url} component={BLCondition} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLConditionDeleteDialog} />
  </>
);

export default Routes;

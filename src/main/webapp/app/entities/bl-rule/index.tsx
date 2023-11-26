import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLRule from './bl-rule';
import BLRuleDetail from './bl-rule-detail';
import BLRuleUpdate from './bl-rule-update';
import BLRuleDeleteDialog from './bl-rule-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLRuleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLRuleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLRuleDetail} />
      <ErrorBoundaryRoute path={match.url} component={BLRule} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLRuleDeleteDialog} />
  </>
);

export default Routes;

import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLMappingParam from './bl-mapping-param';
import BLMappingParamDetail from './bl-mapping-param-detail';
import BLMappingParamUpdate from './bl-mapping-param-update';
import BLMappingParamDeleteDialog from './bl-mapping-param-delete-dialog';
import BLMappingParamList from '../bl-mapping-param/bl-mapping-param-list';
import BLMappingParamApprove from './bl-mapping-param-approve';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLMappingParam} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLMappingParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLMappingParamDetail} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/approve`} component={BLMappingParamApprove} />
      <ErrorBoundaryRoute path={match.url} component={BLMappingParamList} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLMappingParamDeleteDialog} />
  </>
);

export default Routes;

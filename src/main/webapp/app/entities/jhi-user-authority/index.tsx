import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import JhiUserAuthority from './jhi-user-authority';
import JhiUserAuthorityDetail from './jhi-user-authority-detail';
import JhiUserAuthorityUpdate from './jhi-user-authority-update';
import JhiUserAuthorityDeleteDialog from './jhi-user-authority-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={JhiUserAuthorityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={JhiUserAuthorityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={JhiUserAuthorityDetail} />
      <ErrorBoundaryRoute path={match.url} component={JhiUserAuthority} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={JhiUserAuthorityDeleteDialog} />
  </>
);

export default Routes;

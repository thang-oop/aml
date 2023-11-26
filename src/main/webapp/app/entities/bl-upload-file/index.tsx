import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BLUploadFile from './bl-upload-file';
import BLUploadFileDetail from './bl-upload-file-detail';
import BLUploadFileUpdate from './bl-upload-file-new';
import BLUploadFileDeleteDialog from './bl-upload-file-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BLUploadFileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BLUploadFileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BLUploadFileDetail} />
      <ErrorBoundaryRoute path={match.url} component={BLUploadFile} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BLUploadFileDeleteDialog} />
  </>
);

export default Routes;

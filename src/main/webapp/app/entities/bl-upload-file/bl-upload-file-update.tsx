import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bl-upload-file.reducer';
import { IBLUploadFile } from 'app/shared/model/bl-upload-file.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBLUploadFileUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLUploadFileUpdate = (props: IBLUploadFileUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { bLUploadFileEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/bl-upload-file' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateUpload = convertDateTimeToServer(values.dateUpload);
    values.dateAuthorise = convertDateTimeToServer(values.dateAuthorise);

    if (errors.length === 0) {
      const entity = {
        ...bLUploadFileEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="amlApp.bLUploadFile.home.createOrEditLabel" data-cy="BLUploadFileCreateUpdateHeading">
            Create or edit a BLUploadFile
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : bLUploadFileEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="bl-upload-file-id">ID</Label>
                  <AvInput id="bl-upload-file-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="fileNameLabel" for="bl-upload-file-fileName">
                  File Name
                </Label>
                <AvField id="bl-upload-file-fileName" data-cy="fileName" type="text" name="fileName" />
              </AvGroup>
              <AvGroup>
                <Label id="systemFileNameLabel" for="bl-upload-file-systemFileName">
                  System File Name
                </Label>
                <AvField id="bl-upload-file-systemFileName" data-cy="systemFileName" type="text" name="systemFileName" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="bl-upload-file-description">
                  Description
                </Label>
                <AvField id="bl-upload-file-description" data-cy="description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="tagetCompanyLabel" for="bl-upload-file-tagetCompany">
                  Taget Company
                </Label>
                <AvField id="bl-upload-file-tagetCompany" data-cy="tagetCompany" type="text" name="tagetCompany" />
              </AvGroup>
              <AvGroup>
                <Label id="validateLabel" for="bl-upload-file-validate">
                  Validate
                </Label>
                <AvField id="bl-upload-file-validate" data-cy="validate" type="text" name="validate" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceStatusLabel" for="bl-upload-file-serviceStatus">
                  Service Status
                </Label>
                <AvField id="bl-upload-file-serviceStatus" data-cy="serviceStatus" type="text" name="serviceStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="fileSizeLabel" for="bl-upload-file-fileSize">
                  File Size
                </Label>
                <AvField id="bl-upload-file-fileSize" data-cy="fileSize" type="string" className="form-control" name="fileSize" />
              </AvGroup>
              <AvGroup>
                <Label id="recordStatusLabel" for="bl-upload-file-recordStatus">
                  Record Status
                </Label>
                <AvField id="bl-upload-file-recordStatus" data-cy="recordStatus" type="text" name="recordStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="uploadByLabel" for="bl-upload-file-uploadBy">
                  Upload By
                </Label>
                <AvField id="bl-upload-file-uploadBy" data-cy="uploadBy" type="text" name="uploadBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateUploadLabel" for="bl-upload-file-dateUpload">
                  Datetime Upload
                </Label>
                <AvInput
                  id="bl-upload-file-dateUpload"
                  data-cy="dateUpload"
                  type="datetime-local"
                  className="form-control"
                  name="dateUpload"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLUploadFileEntity.dateUpload)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="authoriseByLabel" for="bl-upload-file-authoriseBy">
                  Authorise By
                </Label>
                <AvField id="bl-upload-file-authoriseBy" data-cy="authoriseBy" type="text" name="authoriseBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateAuthoriseLabel" for="bl-upload-file-dateAuthorise">
                  Datetime Authorise
                </Label>
                <AvInput
                  id="bl-upload-file-dateAuthorise"
                  data-cy="dateAuthorise"
                  type="datetime-local"
                  className="form-control"
                  name="dateAuthorise"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLUploadFileEntity.dateAuthorise)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="bl-upload-file-createdBy">
                  Created By
                </Label>
                <AvField id="bl-upload-file-createdBy" data-cy="createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="bl-upload-file-dateCreated">
                  Date Created
                </Label>
                <AvField id="bl-upload-file-dateCreated" data-cy="dateCreated" type="text" name="dateCreated" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bl-upload-file" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  bLUploadFileEntity: storeState.bLUploadFile.entity,
  loading: storeState.bLUploadFile.loading,
  updating: storeState.bLUploadFile.updating,
  updateSuccess: storeState.bLUploadFile.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLUploadFileUpdate);

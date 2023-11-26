import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './jhi-user-authority.reducer';
import { IJhiUserAuthority } from 'app/shared/model/jhi-user-authority.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IJhiUserAuthorityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const JhiUserAuthorityUpdate = (props: IJhiUserAuthorityUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { jhiUserAuthorityEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/jhi-user-authority' + props.location.search);
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
    if (errors.length === 0) {
      const entity = {
        ...jhiUserAuthorityEntity,
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
          <h2 id="amlApp.jhiUserAuthority.home.createOrEditLabel" data-cy="JhiUserAuthorityCreateUpdateHeading">
            Create or edit a JhiUserAuthority
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : jhiUserAuthorityEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="jhi-user-authority-id">ID</Label>
                  <AvInput id="jhi-user-authority-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="userIdLabel" for="jhi-user-authority-userId">
                  User Id
                </Label>
                <AvField id="jhi-user-authority-userId" data-cy="userId" type="text" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="authorityNameLabel" for="jhi-user-authority-authorityName">
                  Authority Name
                </Label>
                <AvField id="jhi-user-authority-authorityName" data-cy="authorityName" type="text" name="authorityName" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/jhi-user-authority" replace color="info">
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
  jhiUserAuthorityEntity: storeState.jhiUserAuthority.entity,
  loading: storeState.jhiUserAuthority.loading,
  updating: storeState.jhiUserAuthority.updating,
  updateSuccess: storeState.jhiUserAuthority.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(JhiUserAuthorityUpdate);

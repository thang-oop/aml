import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bl-paramter.reducer';
import { IBLParamter } from 'app/shared/model/bl-paramter.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBLParamterUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLParamterUpdate = (props: IBLParamterUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { bLParamterEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/bl-paramter' + props.location.search);
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
        ...bLParamterEntity,
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
      <Row>
        <Col md="8">
          <h2 id="amlApp.bLParamter.home.createOrEditLabel" data-cy="BLParamterCreateUpdateHeading">
            Khai báo tham số
          </h2>
        </Col>
      </Row>
      <Row>
        <Col md="8">
          {loading ? (
            <p>Đang tải...</p>
          ) : (
            <AvForm model={isNew ? {} : bLParamterEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="bl-paramter-id">ID</Label>
                  <AvInput id="bl-paramter-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="keyIdLabel" for="bl-paramter-keyId">
                  Tham số
                </Label>
                <AvField id="bl-paramter-keyId" data-cy="keyId" type="text" name="keyId" />
              </AvGroup>
              <AvGroup>
                <Label id="keyValueLabel" for="bl-paramter-keyValue">
                  Giá trị
                </Label>
                <AvField id="bl-paramter-keyValue" data-cy="keyValue" type="text" name="keyValue" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="bl-paramter-description">
                  Diễn giải
                </Label>
                <AvField id="bl-paramter-description" data-cy="description" type="text" name="description" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bl-paramter" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Quay lại</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Cập nhật
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  bLParamterEntity: storeState.bLParamter.entity,
  loading: storeState.bLParamter.loading,
  updating: storeState.bLParamter.updating,
  updateSuccess: storeState.bLParamter.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLParamterUpdate);

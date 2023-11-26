import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bl-customer-pvc.reducer';
import { IBLCustomerPvc } from 'app/shared/model/bl-customer-pvc.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBLCustomerPvcUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLCustomerPvcUpdate = (props: IBLCustomerPvcUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { bLCustomerPvcEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/bl-customer-pvc' + props.location.search);
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
    values.dateCreated = convertDateTimeToServer(values.dateCreated);
    values.dateAuthorise = convertDateTimeToServer(values.dateAuthorise);

    if (errors.length === 0) {
      const entity = {
        ...bLCustomerPvcEntity,
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
          <h2 id="amlApp.bLCustomerPvc.home.createOrEditLabel" data-cy="BLCustomerPvcCreateUpdateHeading">
            Create or edit a BLCustomerPvc
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : bLCustomerPvcEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="bl-customer-pvc-id">ID</Label>
                  <AvInput id="bl-customer-pvc-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="cifLabel" for="bl-customer-pvc-cif">
                  Cif
                </Label>
                <AvField id="bl-customer-pvc-cif" data-cy="cif" type="text" name="cif" />
              </AvGroup>
              <AvGroup>
                <Label id="fullNameLabel" for="bl-customer-pvc-fullName">
                  Full Name
                </Label>
                <AvField id="bl-customer-pvc-fullName" data-cy="fullName" type="text" name="fullName" />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="bl-customer-pvc-dateOfBirth">
                  Date Of Birth
                </Label>
                <AvField id="bl-customer-pvc-dateOfBirth" data-cy="dateOfBirth" type="text" name="dateOfBirth" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdLabel" for="bl-customer-pvc-legalId">
                  Legal Id
                </Label>
                <AvField id="bl-customer-pvc-legalId" data-cy="legalId" type="text" name="legalId" />
              </AvGroup>
              <AvGroup>
                <Label id="legalTypeLabel" for="bl-customer-pvc-legalType">
                  Legal Type
                </Label>
                <AvField id="bl-customer-pvc-legalType" data-cy="legalType" type="text" name="legalType" />
              </AvGroup>
              <AvGroup>
                <Label id="branchLabel" for="bl-customer-pvc-branch">
                  Branch
                </Label>
                <AvField id="bl-customer-pvc-branch" data-cy="branch" type="text" name="branch" />
              </AvGroup>
              <AvGroup>
                <Label id="blCustomerIdLabel" for="bl-customer-pvc-blCustomerId">
                  Bl Customer Id
                </Label>
                <AvField id="bl-customer-pvc-blCustomerId" data-cy="blCustomerId" type="text" name="blCustomerId" />
              </AvGroup>
              <AvGroup>
                <Label id="nameBlLabel" for="bl-customer-pvc-nameBl">
                  Name Bl
                </Label>
                <AvField id="bl-customer-pvc-nameBl" data-cy="nameBl" type="text" name="nameBl" />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthBlLabel" for="bl-customer-pvc-dateOfBirthBl">
                  Date Of Birth Bl
                </Label>
                <AvField id="bl-customer-pvc-dateOfBirthBl" data-cy="dateOfBirthBl" type="text" name="dateOfBirthBl" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdTypeBlLabel" for="bl-customer-pvc-legalIdTypeBl">
                  Legal Id Type Bl
                </Label>
                <AvField id="bl-customer-pvc-legalIdTypeBl" data-cy="legalIdTypeBl" type="text" name="legalIdTypeBl" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdNumberLabel" for="bl-customer-pvc-legalIdNumber">
                  Legal Id Number
                </Label>
                <AvField id="bl-customer-pvc-legalIdNumber" data-cy="legalIdNumber" type="text" name="legalIdNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="matchAttrLabel" for="bl-customer-pvc-matchAttr">
                  Match Attr
                </Label>
                <AvField id="bl-customer-pvc-matchAttr" data-cy="matchAttr" type="text" name="matchAttr" />
              </AvGroup>
              <AvGroup>
                <Label id="valueAttrLabel" for="bl-customer-pvc-valueAttr">
                  Value Attr
                </Label>
                <AvField id="bl-customer-pvc-valueAttr" data-cy="valueAttr" type="text" name="valueAttr" />
              </AvGroup>
              <AvGroup>
                <Label id="weightAttrLabel" for="bl-customer-pvc-weightAttr">
                  Weight Attr
                </Label>
                <AvField id="bl-customer-pvc-weightAttr" data-cy="weightAttr" type="text" name="weightAttr" />
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="bl-customer-pvc-score">
                  Score
                </Label>
                <AvField id="bl-customer-pvc-score" data-cy="score" type="text" name="score" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="bl-customer-pvc-status">
                  Status
                </Label>
                <AvField id="bl-customer-pvc-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="remarkLabel" for="bl-customer-pvc-remark">
                  Remark
                </Label>
                <AvField id="bl-customer-pvc-remark" data-cy="remark" type="text" name="remark" />
              </AvGroup>
              <AvGroup>
                <Label id="recordStatusLabel" for="bl-customer-pvc-recordStatus">
                  Record Status
                </Label>
                <AvField id="bl-customer-pvc-recordStatus" data-cy="recordStatus" type="text" name="recordStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="coCodeLabel" for="bl-customer-pvc-coCode">
                  Co Code
                </Label>
                <AvField id="bl-customer-pvc-coCode" data-cy="coCode" type="text" name="coCode" />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="bl-customer-pvc-createdBy">
                  createdBy
                </Label>
                <AvField id="bl-customer-pvc-createdBy" data-cy="createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="bl-customer-pvc-dateCreated">
                  Date Time Inputt
                </Label>
                <AvInput
                  id="bl-customer-pvc-dateCreated"
                  data-cy="dateCreated"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreated"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLCustomerPvcEntity.dateCreated)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="authoriseByLabel" for="bl-customer-pvc-authoriseBy">
                  authoriseBy
                </Label>
                <AvField id="bl-customer-pvc-authoriseBy" data-cy="authoriseBy" type="text" name="authoriseBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateAuthoriseLabel" for="bl-customer-pvc-dateAuthorise">
                  Date Time Author
                </Label>
                <AvInput
                  id="bl-customer-pvc-dateAuthorise"
                  data-cy="dateAuthorise"
                  type="datetime-local"
                  className="form-control"
                  name="dateAuthorise"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLCustomerPvcEntity.dateAuthorise)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="bl-customer-pvc-createdBy">
                  Created By
                </Label>
                <AvField id="bl-customer-pvc-createdBy" data-cy="createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="bl-customer-pvc-dateCreated">
                  Date Created
                </Label>
                <AvField id="bl-customer-pvc-dateCreated" data-cy="dateCreated" type="text" name="dateCreated" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bl-customer-pvc" replace color="info">
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
  bLCustomerPvcEntity: storeState.bLCustomerPvc.entity,
  loading: storeState.bLCustomerPvc.loading,
  updating: storeState.bLCustomerPvc.updating,
  updateSuccess: storeState.bLCustomerPvc.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCustomerPvcUpdate);

import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bl-customer.reducer';
import { IBLCustomer } from 'app/shared/model/bl-customer.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBLCustomerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLCustomerUpdate = (props: IBLCustomerUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { bLCustomerEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/bl-customer' + props.location.search);
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
    values.otherInfLegal1 = convertDateTimeToServer(values.otherInfLegal1);
    values.otherInfLegal2 = convertDateTimeToServer(values.otherInfLegal2);
    values.dateCreated = convertDateTimeToServer(values.dateCreated);
    values.dateAuthorise = convertDateTimeToServer(values.dateAuthorise);

    if (errors.length === 0) {
      const entity = {
        ...bLCustomerEntity,
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
          <h2 id="amlApp.bLCustomer.home.createOrEditLabel" data-cy="BLCustomerCreateUpdateHeading">
            Create or edit a BLCustomer
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : bLCustomerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="bl-customer-id">ID</Label>
                  <AvInput id="bl-customer-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="fullNameLabel" for="bl-customer-fullName">
                  Full Name
                </Label>
                <AvField id="bl-customer-fullName" data-cy="fullName" type="text" name="fullName" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="bl-customer-firstName">
                  First Name
                </Label>
                <AvField id="bl-customer-firstName" data-cy="firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="bl-customer-lastName">
                  Last Name
                </Label>
                <AvField id="bl-customer-lastName" data-cy="lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="otherName1Label" for="bl-customer-otherName1">
                  Other Name 1
                </Label>
                <AvField id="bl-customer-otherName1" data-cy="otherName1" type="text" name="otherName1" />
              </AvGroup>
              <AvGroup>
                <Label id="otherName2Label" for="bl-customer-otherName2">
                  Other Name 2
                </Label>
                <AvField id="bl-customer-otherName2" data-cy="otherName2" type="text" name="otherName2" />
              </AvGroup>
              <AvGroup>
                <Label id="otherName3Label" for="bl-customer-otherName3">
                  Other Name 3
                </Label>
                <AvField id="bl-customer-otherName3" data-cy="otherName3" type="text" name="otherName3" />
              </AvGroup>
              <AvGroup>
                <Label id="positionBlLabel" for="bl-customer-positionBl">
                  Position Bl
                </Label>
                <AvField id="bl-customer-positionBl" data-cy="positionBl" type="text" name="positionBl" />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthBlLabel" for="bl-customer-dateOfBirthBl">
                  Date Of Birth Bl
                </Label>
                <AvField id="bl-customer-dateOfBirthBl" data-cy="dateOfBirthBl" type="text" name="dateOfBirthBl" />
              </AvGroup>
              <AvGroup>
                <Label id="countryBl1Label" for="bl-customer-countryBl1">
                  Country Bl 1
                </Label>
                <AvField id="bl-customer-countryBl1" data-cy="countryBl1" type="text" name="countryBl1" />
              </AvGroup>
              <AvGroup>
                <Label id="countryBl2Label" for="bl-customer-countryBl2">
                  Country Bl 2
                </Label>
                <AvField id="bl-customer-countryBl2" data-cy="countryBl2" type="text" name="countryBl2" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdTypeBl1Label" for="bl-customer-legalIdTypeBl1">
                  Legal Id Type Bl 1
                </Label>
                <AvField id="bl-customer-legalIdTypeBl1" data-cy="legalIdTypeBl1" type="text" name="legalIdTypeBl1" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdNumber1Label" for="bl-customer-legalIdNumber1">
                  Legal Id Number 1
                </Label>
                <AvField id="bl-customer-legalIdNumber1" data-cy="legalIdNumber1" type="text" name="legalIdNumber1" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdTypeBl2Label" for="bl-customer-legalIdTypeBl2">
                  Legal Id Type Bl 2
                </Label>
                <AvField id="bl-customer-legalIdTypeBl2" data-cy="legalIdTypeBl2" type="text" name="legalIdTypeBl2" />
              </AvGroup>
              <AvGroup>
                <Label id="legalIdNumber2Label" for="bl-customer-legalIdNumber2">
                  Legal Id Number 2
                </Label>
                <AvField id="bl-customer-legalIdNumber2" data-cy="legalIdNumber2" type="text" name="legalIdNumber2" />
              </AvGroup>
              <AvGroup>
                <Label id="otherInfLegal1Label" for="bl-customer-otherInfLegal1">
                  Other Inf Legal 1
                </Label>
                <AvInput
                  id="bl-customer-otherInfLegal1"
                  data-cy="otherInfLegal1"
                  type="datetime-local"
                  className="form-control"
                  name="otherInfLegal1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLCustomerEntity.otherInfLegal1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="otherInfLegal2Label" for="bl-customer-otherInfLegal2">
                  Other Inf Legal 2
                </Label>
                <AvInput
                  id="bl-customer-otherInfLegal2"
                  data-cy="otherInfLegal2"
                  type="datetime-local"
                  className="form-control"
                  name="otherInfLegal2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLCustomerEntity.otherInfLegal2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="addressBl1Label" for="bl-customer-addressBl1">
                  Address Bl 1
                </Label>
                <AvField id="bl-customer-addressBl1" data-cy="addressBl1" type="text" name="addressBl1" />
              </AvGroup>
              <AvGroup>
                <Label id="addressBl2Label" for="bl-customer-addressBl2">
                  Address Bl 2
                </Label>
                <AvField id="bl-customer-addressBl2" data-cy="addressBl2" type="text" name="addressBl2" />
              </AvGroup>
              <AvGroup>
                <Label id="addressNowBl1Label" for="bl-customer-addressNowBl1">
                  Address Now Bl 1
                </Label>
                <AvField id="bl-customer-addressNowBl1" data-cy="addressNowBl1" type="text" name="addressNowBl1" />
              </AvGroup>
              <AvGroup>
                <Label id="addressNowBl2Label" for="bl-customer-addressNowBl2">
                  Address Now Bl 2
                </Label>
                <AvField id="bl-customer-addressNowBl2" data-cy="addressNowBl2" type="text" name="addressNowBl2" />
              </AvGroup>
              <AvGroup>
                <Label id="typeBlLabel" for="bl-customer-typeBl">
                  Type Bl
                </Label>
                <AvField id="bl-customer-typeBl" data-cy="typeBl" type="text" name="typeBl" />
              </AvGroup>
              <AvGroup>
                <Label id="sourceLabel" for="bl-customer-source">
                  Source
                </Label>
                <AvField id="bl-customer-source" data-cy="source" type="text" name="source" />
              </AvGroup>
              <AvGroup>
                <Label id="recordStatusLabel" for="bl-customer-recordStatus">
                  Record Status
                </Label>
                <AvField id="bl-customer-recordStatus" data-cy="recordStatus" type="text" name="recordStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="uploadFileIdLabel" for="bl-customer-uploadFileId">
                  Upload File Id
                </Label>
                <AvField id="bl-customer-uploadFileId" data-cy="uploadFileId" type="text" name="uploadFileId" />
              </AvGroup>
              <AvGroup>
                <Label id="coCodeLabel" for="bl-customer-coCode">
                  Co Code
                </Label>
                <AvField id="bl-customer-coCode" data-cy="coCode" type="text" name="coCode" />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="bl-customer-createdBy">
                  createdBy
                </Label>
                <AvField id="bl-customer-createdBy" data-cy="createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="bl-customer-dateCreated">
                  Date Time Inputt
                </Label>
                <AvInput
                  id="bl-customer-dateCreated"
                  data-cy="dateCreated"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreated"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLCustomerEntity.dateCreated)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="authoriseByLabel" for="bl-customer-authoriseBy">
                  authoriseBy
                </Label>
                <AvField id="bl-customer-authoriseBy" data-cy="authoriseBy" type="text" name="authoriseBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateAuthoriseLabel" for="bl-customer-dateAuthorise">
                  Date Time Author
                </Label>
                <AvInput
                  id="bl-customer-dateAuthorise"
                  data-cy="dateAuthorise"
                  type="datetime-local"
                  className="form-control"
                  name="dateAuthorise"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.bLCustomerEntity.dateAuthorise)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="bl-customer-createdBy">
                  Created By
                </Label>
                <AvField id="bl-customer-createdBy" data-cy="createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="bl-customer-dateCreated">
                  Date Created
                </Label>
                <AvField id="bl-customer-dateCreated" data-cy="dateCreated" type="text" name="dateCreated" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bl-customer" replace color="info">
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
  bLCustomerEntity: storeState.bLCustomer.entity,
  loading: storeState.bLCustomer.loading,
  updating: storeState.bLCustomer.updating,
  updateSuccess: storeState.bLCustomer.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCustomerUpdate);

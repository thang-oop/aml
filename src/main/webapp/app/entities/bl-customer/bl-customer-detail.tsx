import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-customer.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLCustomerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLCustomerDetail = (props: IBLCustomerDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bLCustomerEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bLCustomerDetailsHeading">BLCustomer</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{bLCustomerEntity.id}</dd>
          <dt>
            <span id="fullName">Full Name</span>
          </dt>
          <dd>{bLCustomerEntity.fullName}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{bLCustomerEntity.firstName}</dd>
          <dt>
            <span id="lastName">Last Name</span>
          </dt>
          <dd>{bLCustomerEntity.lastName}</dd>
          <dt>
            <span id="otherName1">Other Name 1</span>
          </dt>
          <dd>{bLCustomerEntity.otherName1}</dd>
          <dt>
            <span id="otherName2">Other Name 2</span>
          </dt>
          <dd>{bLCustomerEntity.otherName2}</dd>
          <dt>
            <span id="otherName3">Other Name 3</span>
          </dt>
          <dd>{bLCustomerEntity.otherName3}</dd>
          <dt>
            <span id="positionBl">Position Bl</span>
          </dt>
          <dd>{bLCustomerEntity.positionBl}</dd>
          <dt>
            <span id="dateOfBirthBl">Date Of Birth Bl</span>
          </dt>
          <dd>{bLCustomerEntity.dateOfBirthBl}</dd>
          <dt>
            <span id="countryBl1">Country Bl 1</span>
          </dt>
          <dd>{bLCustomerEntity.countryBl1}</dd>
          <dt>
            <span id="countryBl2">Country Bl 2</span>
          </dt>
          <dd>{bLCustomerEntity.countryBl2}</dd>
          <dt>
            <span id="legalIdTypeBl1">Legal Id Type Bl 1</span>
          </dt>
          <dd>{bLCustomerEntity.legalIdTypeBl1}</dd>
          <dt>
            <span id="legalIdNumber1">Legal Id Number 1</span>
          </dt>
          <dd>{bLCustomerEntity.legalIdNumber1}</dd>
          <dt>
            <span id="legalIdTypeBl2">Legal Id Type Bl 2</span>
          </dt>
          <dd>{bLCustomerEntity.legalIdTypeBl2}</dd>
          <dt>
            <span id="legalIdNumber2">Legal Id Number 2</span>
          </dt>
          <dd>{bLCustomerEntity.legalIdNumber2}</dd>
          <dt>
            <span id="otherInfLegal1">Other Inf Legal 1</span>
          </dt>
          <dd>
            {bLCustomerEntity.otherInfLegal1 ? (
              <TextFormat value={bLCustomerEntity.otherInfLegal1} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="otherInfLegal2">Other Inf Legal 2</span>
          </dt>
          <dd>
            {bLCustomerEntity.otherInfLegal2 ? (
              <TextFormat value={bLCustomerEntity.otherInfLegal2} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="addressBl1">Address Bl 1</span>
          </dt>
          <dd>{bLCustomerEntity.addressBl1}</dd>
          <dt>
            <span id="addressBl2">Address Bl 2</span>
          </dt>
          <dd>{bLCustomerEntity.addressBl2}</dd>
          <dt>
            <span id="addressNowBl1">Address Now Bl 1</span>
          </dt>
          <dd>{bLCustomerEntity.addressNowBl1}</dd>
          <dt>
            <span id="addressNowBl2">Address Now Bl 2</span>
          </dt>
          <dd>{bLCustomerEntity.addressNowBl2}</dd>
          <dt>
            <span id="typeBl">Type Bl</span>
          </dt>
          <dd>{bLCustomerEntity.typeBl}</dd>
          <dt>
            <span id="source">Source</span>
          </dt>
          <dd>{bLCustomerEntity.source}</dd>
          <dt>
            <span id="recordStatus">Record Status</span>
          </dt>
          <dd>{bLCustomerEntity.recordStatus}</dd>
          <dt>
            <span id="uploadFileId">Upload File Id</span>
          </dt>
          <dd>{bLCustomerEntity.uploadFileId}</dd>
          <dt>
            <span id="coCode">Co Code</span>
          </dt>
          <dd>{bLCustomerEntity.coCode}</dd>
          <dt>
            <span id="createdBy">createdBy</span>
          </dt>
          <dd>{bLCustomerEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Date Time Inputt</span>
          </dt>
          <dd>
            {bLCustomerEntity.dateCreated ? <TextFormat value={bLCustomerEntity.dateCreated} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="authoriseBy">authoriseBy</span>
          </dt>
          <dd>{bLCustomerEntity.authoriseBy}</dd>
          <dt>
            <span id="dateAuthorise">Date Time Author</span>
          </dt>
          <dd>
            {bLCustomerEntity.dateAuthorise ? (
              <TextFormat value={bLCustomerEntity.dateAuthorise} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{bLCustomerEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Date Created</span>
          </dt>
          <dd>{bLCustomerEntity.dateCreated}</dd>
        </dl>
        <Button tag={Link} to="/bl-customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bl-customer/${bLCustomerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bLCustomer }: IRootState) => ({
  bLCustomerEntity: bLCustomer.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCustomerDetail);

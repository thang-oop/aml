import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-customer-pvc.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLCustomerPvcDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLCustomerPvcDetail = (props: IBLCustomerPvcDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bLCustomerPvcEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bLCustomerPvcDetailsHeading">BLCustomerPvc</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{bLCustomerPvcEntity.id}</dd>
          <dt>
            <span id="cif">Cif</span>
          </dt>
          <dd>{bLCustomerPvcEntity.cif}</dd>
          <dt>
            <span id="fullName">Full Name</span>
          </dt>
          <dd>{bLCustomerPvcEntity.fullName}</dd>
          <dt>
            <span id="dateOfBirth">Date Of Birth</span>
          </dt>
          <dd>{bLCustomerPvcEntity.dateOfBirth}</dd>
          <dt>
            <span id="legalId">Legal Id</span>
          </dt>
          <dd>{bLCustomerPvcEntity.legalId}</dd>
          <dt>
            <span id="legalType">Legal Type</span>
          </dt>
          <dd>{bLCustomerPvcEntity.legalType}</dd>
          <dt>
            <span id="branch">Branch</span>
          </dt>
          <dd>{bLCustomerPvcEntity.branch}</dd>
          <dt>
            <span id="blCustomerId">Bl Customer Id</span>
          </dt>
          <dd>{bLCustomerPvcEntity.blCustomerId}</dd>
          <dt>
            <span id="nameBl">Name Bl</span>
          </dt>
          <dd>{bLCustomerPvcEntity.nameBl}</dd>
          <dt>
            <span id="dateOfBirthBl">Date Of Birth Bl</span>
          </dt>
          <dd>{bLCustomerPvcEntity.dateOfBirthBl}</dd>
          <dt>
            <span id="legalIdTypeBl">Legal Id Type Bl</span>
          </dt>
          <dd>{bLCustomerPvcEntity.legalIdTypeBl}</dd>
          <dt>
            <span id="legalIdNumber">Legal Id Number</span>
          </dt>
          <dd>{bLCustomerPvcEntity.legalIdNumber}</dd>
          <dt>
            <span id="matchAttr">Match Attr</span>
          </dt>
          <dd>{bLCustomerPvcEntity.matchAttr}</dd>
          <dt>
            <span id="valueAttr">Value Attr</span>
          </dt>
          <dd>{bLCustomerPvcEntity.valueAttr}</dd>
          <dt>
            <span id="weightAttr">Weight Attr</span>
          </dt>
          <dd>{bLCustomerPvcEntity.weightAttr}</dd>
          <dt>
            <span id="score">Score</span>
          </dt>
          <dd>{bLCustomerPvcEntity.score}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{bLCustomerPvcEntity.status}</dd>
          <dt>
            <span id="remark">Remark</span>
          </dt>
          <dd>{bLCustomerPvcEntity.remark}</dd>
          <dt>
            <span id="recordStatus">Record Status</span>
          </dt>
          <dd>{bLCustomerPvcEntity.recordStatus}</dd>
          <dt>
            <span id="coCode">Co Code</span>
          </dt>
          <dd>{bLCustomerPvcEntity.coCode}</dd>
          <dt>
            <span id="createdBy">createdBy</span>
          </dt>
          <dd>{bLCustomerPvcEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Date Time Inputt</span>
          </dt>
          <dd>
            {bLCustomerPvcEntity.dateCreated ? (
              <TextFormat value={bLCustomerPvcEntity.dateCreated} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="authoriseBy">authoriseBy</span>
          </dt>
          <dd>{bLCustomerPvcEntity.authoriseBy}</dd>
          <dt>
            <span id="dateAuthorise">Date Time Author</span>
          </dt>
          <dd>
            {bLCustomerPvcEntity.dateAuthorise ? (
              <TextFormat value={bLCustomerPvcEntity.dateAuthorise} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{bLCustomerPvcEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Date Created</span>
          </dt>
          <dd>{bLCustomerPvcEntity.dateCreated}</dd>
        </dl>
        <Button tag={Link} to="/bl-customer-pvc" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bl-customer-pvc/${bLCustomerPvcEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bLCustomerPvc }: IRootState) => ({
  bLCustomerPvcEntity: bLCustomerPvc.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCustomerPvcDetail);

import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-condition.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLConditionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLConditionDetail = (props: IBLConditionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bLConditionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bLConditionDetailsHeading">BLCondition</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{bLConditionEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{bLConditionEntity.name}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{bLConditionEntity.description}</dd>
          <dt>
            <span id="blackListFlds">Black List Flds</span>
          </dt>
          <dd>{bLConditionEntity.blackListFlds}</dd>
          <dt>
            <span id="customerFlds">Customer Flds</span>
          </dt>
          <dd>{bLConditionEntity.customerFlds}</dd>
          <dt>
            <span id="weightPoint">Weight Point</span>
          </dt>
          <dd>{bLConditionEntity.weightPoint}</dd>
          <dt>
            <span id="ruleId">Rule Id</span>
          </dt>
          <dd>{bLConditionEntity.ruleId}</dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{bLConditionEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Date Created</span>
          </dt>
          <dd>{bLConditionEntity.dateCreated}</dd>
          <dt>
            <span id="authoriseBy">authoriseBy By</span>
          </dt>
          <dd>{bLConditionEntity.authoriseBy}</dd>
          <dt>
            <span id="dateauthoriseBy">Date authoriseBy</span>
          </dt>
          <dd>{bLConditionEntity.dateauthoriseBy}</dd>
        </dl>
        <Button tag={Link} to="/bl-condition" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bl-condition/${bLConditionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bLCondition }: IRootState) => ({
  bLConditionEntity: bLCondition.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLConditionDetail);

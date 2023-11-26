import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-rule.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLRuleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLRuleDetail = (props: IBLRuleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bLRuleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bLRuleDetailsHeading">Thông tin rule & policy</h2>
        <dl className="jh-entity-details">
          <dd>{bLRuleEntity.id}</dd>
          <dt>
            <span id="name">Tên rule</span>
          </dt>
          <dd>{bLRuleEntity.name}</dd>
          <dt>
            <span id="description">Mô tả</span>
          </dt>
          <dd>{bLRuleEntity.description}</dd>
          <dt>
            <span id="sourceIds">Nguồn dữ liệu</span>
          </dt>
          <dd>{bLRuleEntity.sourceIds}</dd>
          <dt>
            <span id="customerType">Loại khách hàng</span>
          </dt>
          <dd>{bLRuleEntity.customerType}</dd>
          <dt>
            <span id="scoreMinimum">Điểm tối thiểu</span>
          </dt>
          <dd>{bLRuleEntity.scoreMinimum}</dd>
          <dt>
            <span id="createdBy">Người tạo</span>
          </dt>
          <dd>{bLRuleEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Ngày tạo</span>
          </dt>
          <dd>{bLRuleEntity.dateCreated}</dd>
          <dt>
            <span id="authoriseBy">Người duyệt</span>
          </dt>
          <dd>{bLRuleEntity.authoriseBy}</dd>
          <dt>
            <span id="dateAuthorise">Ngày duyệt</span>
          </dt>
          <dd>{bLRuleEntity.dateAuthorise}</dd>
        </dl>
        <Button tag={Link} to="/bl-rule" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Quay lại</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bl-rule/${bLRuleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Phê duyệt</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bLRule }: IRootState) => ({
  bLRuleEntity: bLRule.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLRuleDetail);

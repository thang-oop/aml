import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-paramter.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLParamterDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLParamterDetail = (props: IBLParamterDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bLParamterEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bLParamterDetailsHeading">Khai báo tham số</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="keyId">Tham số</span>
          </dt>
          <dd>{bLParamterEntity.keyId}</dd>
          <dt>
            <span id="keyValue">Giá trị</span>
          </dt>
          <dd>{bLParamterEntity.keyValue}</dd>
          <dt>
            <span id="description">Diễn giải</span>
          </dt>
          <dd>{bLParamterEntity.description}</dd>
          <dt>
            <span id="description">Trạng thái</span>
          </dt>
          <dd>{bLParamterEntity.recordStatus}</dd>
          <dt>
            <span id="createdBy">Người tạo</span>
          </dt>
          <dd>{bLParamterEntity.createdBy}</dd>
          <dt>
            <span id="dateCreated">Ngày tạo</span>
          </dt>
          <dd>{bLParamterEntity.dateCreated}</dd>
          <dt>
            <span id="createdBy">Người duyệt</span>
          </dt>
          <dd>{bLParamterEntity.authoriseBy}</dd>
          <dt>
            <span id="dateCreated">Ngày duyệt</span>
          </dt>
          <dd>{bLParamterEntity.dateauthoriseBy}</dd>
        </dl>
        <Button tag={Link} to="/bl-paramter" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Quay lại</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bl-paramter/${bLParamterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bLParamter }: IRootState) => ({
  bLParamterEntity: bLParamter.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLParamterDetail);

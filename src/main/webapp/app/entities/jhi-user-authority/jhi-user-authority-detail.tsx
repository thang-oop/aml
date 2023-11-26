import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './jhi-user-authority.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IJhiUserAuthorityDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const JhiUserAuthorityDetail = (props: IJhiUserAuthorityDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { jhiUserAuthorityEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="jhiUserAuthorityDetailsHeading">JhiUserAuthority</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{jhiUserAuthorityEntity.id}</dd>
          <dt>
            <span id="userId">User Id</span>
          </dt>
          <dd>{jhiUserAuthorityEntity.userId}</dd>
          <dt>
            <span id="authorityName">Authority Name</span>
          </dt>
          <dd>{jhiUserAuthorityEntity.authorityName}</dd>
        </dl>
        <Button tag={Link} to="/jhi-user-authority" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/jhi-user-authority/${jhiUserAuthorityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ jhiUserAuthority }: IRootState) => ({
  jhiUserAuthorityEntity: jhiUserAuthority.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(JhiUserAuthorityDetail);

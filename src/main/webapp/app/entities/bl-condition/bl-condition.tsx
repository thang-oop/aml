import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bl-condition.reducer';
import { IBLCondition } from 'app/shared/model/bl-condition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLConditionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLCondition = (props: IBLConditionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { bLConditionList, match, loading } = props;
  return (
    <div>
      <h2 id="bl-condition-heading" data-cy="BLConditionHeading">
        Danh sách điều kiện
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Thêm mới
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bLConditionList && bLConditionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>Điều kiện</th>
                <th>Mô tả</th>
                <th>Danh sách trường (Blacklist)</th>
                <th>Danh sách trường (Customer)</th>
                <th>Trọng số</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bLConditionList.map((bLCondition, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${bLCondition.id}`} color="link" size="sm">
                      {bLCondition.name}
                    </Button>
                  </td>
                  <td>{bLCondition.description}</td>
                  <td>{bLCondition.blackListFlds}</td>
                  <td>{bLCondition.customerFlds}</td>
                  <td>{bLCondition.weightPoint}</td>
                  <td>{bLCondition.ruleId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bLCondition.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bLCondition.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bLCondition.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">Không có dữ liệu! </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ bLCondition }: IRootState) => ({
  bLConditionList: bLCondition.entities,
  loading: bLCondition.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCondition);

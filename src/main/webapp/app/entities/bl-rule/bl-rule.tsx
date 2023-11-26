import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bl-rule.reducer';
import { getSourceDataEntity } from '../bl-mapping-param/bl-mapping-param.reducer';

import { IBLRule } from 'app/shared/model/bl-rule.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLRuleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLRule = (props: IBLRuleProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { bLRuleList, sourceDataDetail, match, loading } = props;
  return (
    <div>
      <h2 id="bl-rule-heading" data-cy="BLRuleHeading">
        Quản lý rules và policies
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
        {bLRuleList && bLRuleList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>Tên rule</th>
                <th>Mô tả</th>
                <th>Nguồn dữ liệu</th>
                <th>Loại khách hàng</th>
                <th>Diểm tối thiểu</th>
                <th>Trạng thái</th>
                <th>Người tạo/Ngày tạo</th>
                <th>Người duyệt/Ngày duyệt</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bLRuleList.map((bLRule, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${bLRule.id}`} color="link" size="sm">
                      {bLRule.name}
                    </Button>
                  </td>
                  <td>{bLRule.description}</td>
                  <td>{bLRule.sourceIds}</td>
                  <td>{bLRule.customerType}</td>
                  <td>{bLRule.scoreMinimum}</td>
                  <td>{bLRule.recordStatus}</td>
                  <td>
                    {bLRule.createdBy}/{bLRule.dateCreated}
                  </td>
                  <td>
                    {bLRule.authoriseBy}/{bLRule.dateAuthorise}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bLRule.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Duyệt</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bLRule.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bLRule.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Xóa</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No rules found !</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ bLRule, bLMappingParam }: IRootState) => ({
  bLRuleList: bLRule.entities,
  sourceDataDetail: bLMappingParam.entity,
  loading: bLRule.loading,
});

const mapDispatchToProps = {
  getEntities,
  getSourceDataEntity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLRule);

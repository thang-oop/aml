import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, Input, FormGroup, Table } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getEntities } from './bl-mapping-param.reducer';
import { IRootState } from 'app/shared/reducers';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { getSortState, JhiItemCount, JhiPagination } from 'react-jhipster';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IBLCustomerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLMappingParamList = (props: IBLCustomerProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { blMappingParamList, match, loading, totalItems } = props;
  return (
    <div>
      <Row>
        <Col md="12">
          <h2 id="amlApp.bLCustomer.home.createOrEditLabel" data-cy="BLCustomerCreateUpdateHeading">
            Danh sách nguồn dữ liệu
          </h2>
        </Col>
      </Row>

      <Row>
        <Col md="3">
          <AvForm>
            <AvGroup>
              <Label id="keyIdLabel" for="bl-paramter-keyId">
                Nguồn dữ liệu
              </Label>
              <AvField id="bl-paramter-sourceName" data-cy="keyId" type="text" name="sourceName" />
            </AvGroup>
            <AvGroup>
              <Label id="prefixLabel" for="bl-paramter-keyId">
                Tiền tố file:
              </Label>
              <AvField id="bl-paramter-sourcePrefixFix" data-cy="keyId" type="text" name="sourceFilePrefix" />
            </AvGroup>
            &nbsp;
            <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit">
              <FontAwesomeIcon icon="search" />
              &nbsp; Tra cứu
            </Button>
            &nbsp;
            <Link
              to="/bl-mapping-param/new"
              className="btn btn-primary jh-create-entity"
              id="jh-create-entity"
              data-cy="entityCreateButton"
            >
              <FontAwesomeIcon icon="plus" />
              &nbsp; Thêm mới
            </Link>
          </AvForm>
        </Col>
      </Row>

      <br />

      <div className="table-responsive">
        {blMappingParamList && blMappingParamList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand">
                  Loại danh sách <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Tiền tố <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Người tạo <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Ngày tạo <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Người duyệt <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Ngày duyệt <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Trạng thái <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand">
                  Thao tác <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {blMappingParamList.map((bLMappingParamter, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{bLMappingParamter.sourceName}</td>
                  <td>{bLMappingParamter.sourceFilePrefix}</td>
                  <td>{bLMappingParamter.createdBy}</td>
                  <td>{bLMappingParamter.dateCreated}</td>
                  <td>{bLMappingParamter.authoriseBy}</td>
                  <td>{bLMappingParamter.dateAuthorise}</td>
                  <td>{bLMappingParamter.recordStatus}</td>
                  <td>
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLMappingParamter.id}/approve?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="success"
                        size="sm"
                        data-cy="entityEditButton"
                        disabled={
                          bLMappingParamter.recordStatus === 'Đã duyệt' || bLMappingParamter.recordStatus === 'Từ chối' ? true : false
                        }
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Duyệt</span>
                      </Button>

                      <Button tag={Link} to={`${match.url}/${bLMappingParamter.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Xem</span>
                      </Button>

                      <Button
                        tag={Link}
                        to={`${match.url}/${bLMappingParamter.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                        disabled={
                          bLMappingParamter.recordStatus === 'Đã duyệt' || bLMappingParamter.recordStatus === 'Từ chối' ? true : false
                        }
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLMappingParamter.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Xóa</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No data found</div>
        )}
      </div>
      {props.totalItems ? (
        <div className={blMappingParamList && blMappingParamList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={props.totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

const mapStateToProps = ({ bLMappingParam }: IRootState) => ({
  blMappingParamList: bLMappingParam.entities,
  loading: bLMappingParam.loading,
  totalItems: bLMappingParam.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLMappingParamList);

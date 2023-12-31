import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bl-customer-pvc.reducer';
import { IBLCustomerPvc } from 'app/shared/model/bl-customer-pvc.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IBLCustomerPvcProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLCustomerPvc = (props: IBLCustomerPvcProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

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

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get('sort');
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { bLCustomerPvcList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="bl-customer-pvc-heading" data-cy="BLCustomerPvcHeading">
        BL Customer Pvcs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new BL Customer Pvc
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bLCustomerPvcList && bLCustomerPvcList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('cif')}>
                  Cif <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fullName')}>
                  Full Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateOfBirth')}>
                  Date Of Birth <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalId')}>
                  Legal Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalType')}>
                  Legal Type <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('branch')}>
                  Branch <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('blCustomerId')}>
                  Bl Customer Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nameBl')}>
                  Name Bl <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateOfBirthBl')}>
                  Date Of Birth Bl <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalIdTypeBl')}>
                  Legal Id Type Bl <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalIdNumber')}>
                  Legal Id Number <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('matchAttr')}>
                  Match Attr <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('valueAttr')}>
                  Value Attr <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('weightAttr')}>
                  Weight Attr <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('score')}>
                  Score <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('remark')}>
                  Remark <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  Record Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('coCode')}>
                  Co Code <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  createdBy <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateCreated')}>
                  Date Time Inputt <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('authoriseBy')}>
                  authoriseBy <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateAuthorise')}>
                  Date Time Author <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  Created By <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateCreated')}>
                  Date Created <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bLCustomerPvcList.map((bLCustomerPvc, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${bLCustomerPvc.id}`} color="link" size="sm">
                      {bLCustomerPvc.id}
                    </Button>
                  </td>
                  <td>{bLCustomerPvc.cif}</td>
                  <td>{bLCustomerPvc.fullName}</td>
                  <td>{bLCustomerPvc.dateOfBirth}</td>
                  <td>{bLCustomerPvc.legalId}</td>
                  <td>{bLCustomerPvc.legalType}</td>
                  <td>{bLCustomerPvc.branch}</td>
                  <td>{bLCustomerPvc.blCustomerId}</td>
                  <td>{bLCustomerPvc.nameBl}</td>
                  <td>{bLCustomerPvc.dateOfBirthBl}</td>
                  <td>{bLCustomerPvc.legalIdTypeBl}</td>
                  <td>{bLCustomerPvc.legalIdNumber}</td>
                  <td>{bLCustomerPvc.matchAttr}</td>
                  <td>{bLCustomerPvc.valueAttr}</td>
                  <td>{bLCustomerPvc.weightAttr}</td>
                  <td>{bLCustomerPvc.score}</td>
                  <td>{bLCustomerPvc.status}</td>
                  <td>{bLCustomerPvc.remark}</td>
                  <td>{bLCustomerPvc.recordStatus}</td>
                  <td>{bLCustomerPvc.coCode}</td>
                  <td>{bLCustomerPvc.createdBy}</td>
                  <td>
                    {bLCustomerPvc.dateCreated ? (
                      <TextFormat type="date" value={bLCustomerPvc.dateCreated} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{bLCustomerPvc.authoriseBy}</td>
                  <td>
                    {bLCustomerPvc.dateAuthorise ? (
                      <TextFormat type="date" value={bLCustomerPvc.dateAuthorise} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{bLCustomerPvc.createdBy}</td>
                  <td>{bLCustomerPvc.dateCreated}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bLCustomerPvc.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLCustomerPvc.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLCustomerPvc.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No BL Customer Pvcs found</div>
        )}
      </div>
      {props.totalItems ? (
        <div className={bLCustomerPvcList && bLCustomerPvcList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ bLCustomerPvc }: IRootState) => ({
  bLCustomerPvcList: bLCustomerPvc.entities,
  loading: bLCustomerPvc.loading,
  totalItems: bLCustomerPvc.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCustomerPvc);

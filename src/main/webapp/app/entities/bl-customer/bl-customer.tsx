import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bl-customer.reducer';
import { IBLCustomer } from 'app/shared/model/bl-customer.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IBLCustomerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLCustomer = (props: IBLCustomerProps) => {
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

  const { bLCustomerList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="bl-customer-heading" data-cy="BLCustomerHeading">
        BL Customers
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new BL Customer
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bLCustomerList && bLCustomerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fullName')}>
                  Full Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firstName')}>
                  First Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lastName')}>
                  Last Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otherName1')}>
                  Other Name 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otherName2')}>
                  Other Name 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otherName3')}>
                  Other Name 3 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('positionBl')}>
                  Position Bl <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateOfBirthBl')}>
                  Date Of Birth Bl <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('countryBl1')}>
                  Country Bl 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('countryBl2')}>
                  Country Bl 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalIdTypeBl1')}>
                  Legal Id Type Bl 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalIdNumber1')}>
                  Legal Id Number 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalIdTypeBl2')}>
                  Legal Id Type Bl 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('legalIdNumber2')}>
                  Legal Id Number 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otherInfLegal1')}>
                  Other Inf Legal 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otherInfLegal2')}>
                  Other Inf Legal 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('addressBl1')}>
                  Address Bl 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('addressBl2')}>
                  Address Bl 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('addressNowBl1')}>
                  Address Now Bl 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('addressNowBl2')}>
                  Address Now Bl 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('typeBl')}>
                  Type Bl <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('source')}>
                  Source <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  Record Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uploadFileId')}>
                  Upload File Id <FontAwesomeIcon icon="sort" />
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
              {bLCustomerList.map((bLCustomer, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${bLCustomer.id}`} color="link" size="sm">
                      {bLCustomer.id}
                    </Button>
                  </td>
                  <td>{bLCustomer.fullName}</td>
                  <td>{bLCustomer.firstName}</td>
                  <td>{bLCustomer.lastName}</td>
                  <td>{bLCustomer.otherName1}</td>
                  <td>{bLCustomer.otherName2}</td>
                  <td>{bLCustomer.otherName3}</td>
                  <td>{bLCustomer.positionBl}</td>
                  <td>{bLCustomer.dateOfBirthBl}</td>
                  <td>{bLCustomer.countryBl1}</td>
                  <td>{bLCustomer.countryBl2}</td>
                  <td>{bLCustomer.legalIdTypeBl1}</td>
                  <td>{bLCustomer.legalIdNumber1}</td>
                  <td>{bLCustomer.legalIdTypeBl2}</td>
                  <td>{bLCustomer.legalIdNumber2}</td>
                  <td>
                    {bLCustomer.otherInfLegal1 ? (
                      <TextFormat type="date" value={bLCustomer.otherInfLegal1} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {bLCustomer.otherInfLegal2 ? (
                      <TextFormat type="date" value={bLCustomer.otherInfLegal2} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{bLCustomer.addressBl1}</td>
                  <td>{bLCustomer.addressBl2}</td>
                  <td>{bLCustomer.addressNowBl1}</td>
                  <td>{bLCustomer.addressNowBl2}</td>
                  <td>{bLCustomer.typeBl}</td>
                  <td>{bLCustomer.source}</td>
                  <td>{bLCustomer.recordStatus}</td>
                  <td>{bLCustomer.uploadFileId}</td>
                  <td>{bLCustomer.coCode}</td>
                  <td>{bLCustomer.createdBy}</td>
                  <td>
                    {bLCustomer.dateCreated ? <TextFormat type="date" value={bLCustomer.dateCreated} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{bLCustomer.authoriseBy}</td>
                  <td>
                    {bLCustomer.dateAuthorise ? <TextFormat type="date" value={bLCustomer.dateAuthorise} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{bLCustomer.createdBy}</td>
                  <td>{bLCustomer.dateCreated}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bLCustomer.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLCustomer.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLCustomer.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
          !loading && <div className="alert alert-warning">No BL Customers found</div>
        )}
      </div>
      {props.totalItems ? (
        <div className={bLCustomerList && bLCustomerList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ bLCustomer }: IRootState) => ({
  bLCustomerList: bLCustomer.entities,
  loading: bLCustomer.loading,
  totalItems: bLCustomer.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLCustomer);

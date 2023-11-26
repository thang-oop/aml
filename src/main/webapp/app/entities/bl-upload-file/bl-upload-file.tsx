import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bl-upload-file.reducer';
import { IBLUploadFile } from 'app/shared/model/bl-upload-file.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IBLUploadFileProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLUploadFile = (props: IBLUploadFileProps) => {
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

  const { bLUploadFileList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="bl-upload-file-heading" data-cy="BLUploadFileHeading">
        BL Upload Files
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new BL Upload File
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bLUploadFileList && bLUploadFileList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fileName')}>
                  File Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('systemFileName')}>
                  System File Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  Description <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tagetCompany')}>
                  Taget Company <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('validate')}>
                  Validate <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceStatus')}>
                  Service Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fileSize')}>
                  File Size <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  Record Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uploadBy')}>
                  Upload By <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateUpload')}>
                  Datetime Upload <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('authoriseBy')}>
                  Authorise By <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateAuthorise')}>
                  Datetime Authorise <FontAwesomeIcon icon="sort" />
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
              {bLUploadFileList.map((bLUploadFile, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${bLUploadFile.id}`} color="link" size="sm">
                      {bLUploadFile.id}
                    </Button>
                  </td>
                  <td>{bLUploadFile.fileName}</td>
                  <td>{bLUploadFile.systemFileName}</td>
                  <td>{bLUploadFile.description}</td>
                  <td>{bLUploadFile.tagetCompany}</td>
                  <td>{bLUploadFile.validate}</td>
                  <td>{bLUploadFile.serviceStatus}</td>
                  <td>{bLUploadFile.fileSize}</td>
                  <td>{bLUploadFile.recordStatus}</td>
                  <td>{bLUploadFile.uploadBy}</td>
                  <td>
                    {bLUploadFile.dateUpload ? <TextFormat type="date" value={bLUploadFile.dateUpload} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{bLUploadFile.authoriseBy}</td>
                  <td>
                    {bLUploadFile.dateAuthorise ? (
                      <TextFormat type="date" value={bLUploadFile.dateAuthorise} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{bLUploadFile.createdBy}</td>
                  <td>{bLUploadFile.dateCreated}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bLUploadFile.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLUploadFile.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${bLUploadFile.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
          !loading && <div className="alert alert-warning">No BL Upload Files found</div>
        )}
      </div>
      {props.totalItems ? (
        <div className={bLUploadFileList && bLUploadFileList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ bLUploadFile }: IRootState) => ({
  bLUploadFileList: bLUploadFile.entities,
  loading: bLUploadFile.loading,
  totalItems: bLUploadFile.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLUploadFile);

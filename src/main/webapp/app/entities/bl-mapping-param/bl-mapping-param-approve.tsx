import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, Input } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import { getEntity, updateEntity, createEntity, reset } from './bl-mapping-param.reducer';
import { Select, Typography } from 'antd';
import { IBLMappingParam } from 'app/shared/model/bl-mapping-param.model';
export interface IBLMappingParamApproveProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLMappingParamApprove = (props: IBLMappingParamApproveProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const { bLMappingParamEntity, updating, account } = props;
  const [columName, setColumName] = useState([]);
  const handleClose = () => {
    props.history.push('/bl-mapping-param' + props.location.search);
  };
  const [selectedOptionsPerRow, setSelectedOptionsPerRow] = useState([]);
  const [selectedDataRow, setSelectedDataRow] = useState([]);
  const [dataRow, setDataRow] = useState([]);

  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  useEffect(() => {
    const sourceCols = (bLMappingParamEntity?.sourceRef || '').split(',');

    setColumName(sourceCols);
    const rowList = (bLMappingParamEntity?.sourceCols || '').split('||');
    const listObj = rowList.map(obj => {
      const arrRow = obj.split(':');
      const arrValue = arrRow[1]?.split(',');
      const objRow = {
        [arrRow[0]]: arrValue,
      };
      return objRow;
    });

    setDataRow(listObj);
  }, [bLMappingParamEntity, selectedDataRow]);

  useEffect(() => {
    const arrOption = [];
    for (let i = 0; i < columName.length; i++) {
      arrOption.push({
        label: columName[i].toString(),
        value: columName[i].toString(),
      });
    }

    const updatedSelectedOptions = [...selectedOptionsPerRow];
    for (let i = 0; i < selectedDataRow.length; i++) {
      const key = Object.keys(selectedDataRow[i]).join();
      updatedSelectedOptions[i] = selectedDataRow[i][key];
    }
    setSelectedOptionsPerRow(updatedSelectedOptions);
  }, [selectedDataRow, dataRow]);

  const currentDate = new Date();
  const formattedDate = `${padNumber(currentDate.getDate())}/${padNumber(
    currentDate.getMonth() + 1
  )}/${currentDate.getFullYear()} ${padNumber(currentDate.getHours())}:${padNumber(currentDate.getMinutes())}:${padNumber(
    currentDate.getSeconds()
  )}`;

  function padNumber(number) {
    return number.toString().padStart(2, '0');
  }

  const approve = isApprove => {
    const entity: IBLMappingParam = {
      id: bLMappingParamEntity.id,
      sourceName: bLMappingParamEntity.sourceName,
      sourceFilePrefix: bLMappingParamEntity.sourceFilePrefix,
      sourceCols: bLMappingParamEntity.sourceCols,
      sourceRef: bLMappingParamEntity.sourceRef,
      recordStatus: isApprove ? 'Đã duyệt' : 'Từ chối',
      createdBy: bLMappingParamEntity.createdBy,
      dateCreated: bLMappingParamEntity.dateCreated,
      authoriseBy: account.login,
      dateAuthorise: formattedDate,
    };
    props.updateEntity(entity);
  };

  return (
    <AvForm>
      <Row>
        <Col>
          <h2 id="amlApp.bLCustomer.home.createOrEditLabel" data-cy="BLCustomerCreateUpdateHeading">
            Phê duyệt khai báo nguồn dữ liệu
          </h2>
        </Col>
      </Row>
      <Row>
        <Col md="8">
          <AvForm model={isNew ? {} : bLMappingParamEntity}>
            <AvGroup>
              <Label id="keyIdLabel" for="bl-paramter-sourceName">
                Nguồn dữ liệu
              </Label>
              <AvField
                id="bl-paramter-sourceName"
                data-cy="sourceName"
                type="text"
                name="sourceName"
                value={bLMappingParamEntity.sourceName || ''}
                disabled
              />
            </AvGroup>
            <AvGroup>
              <Label id="prefixLabel" for="bl-paramter-keyId">
                Tiền tố file:
              </Label>
              <AvInput
                id="bl-paramter-sourcePrefixFix"
                data-cy="keyId"
                type="text"
                name="sourceFilePrefix"
                value={bLMappingParamEntity.sourceFilePrefix || ''}
                disabled
              />
            </AvGroup>
          </AvForm>
        </Col>
      </Row>
      <Row>
        <Col md="8">
          <Button color="success" id="save-entity" data-cy="entityCreateSaveButton" type="submit" onClick={() => approve(true)}>
            <FontAwesomeIcon icon="delicious" />
            &nbsp; Phê duyệt
          </Button>
          &nbsp;
          <Button color="danger" id="save-entity" data-cy="entityCreateSaveButton" type="submit" onClick={() => approve(false)}>
            <FontAwesomeIcon icon="save" />
            &nbsp; Từ chối
          </Button>
          &nbsp;
          <Button tag={Link} id="cancel-save" to="/bl-mapping-param" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />
            &nbsp;
            <span className="d-none d-md-inline">Quay lại</span>
          </Button>
        </Col>
      </Row>

      <Row>
        <Col md="8">
          <AvGroup>
            <br />
            <div className="table-responsive">
              <table className="table" style={{ margin: '0 auto' }}>
                <thead>
                  <tr>
                    <th style={{ width: '50%', textAlign: 'center' }}>Template Cols</th>
                    <th style={{ width: '50%', textAlign: 'center' }}>Source Cols</th>
                  </tr>
                </thead>
                <tbody>
                  {dataRow.map((col, j) => {
                    const key = Object.keys(col).join();
                    return (
                      <tr key={key} className="item">
                        <td>{key}</td>
                        <td>
                          <Select mode="multiple" style={{ width: '100%' }} defaultValue={col[key]} />
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          </AvGroup>
          <br />
          <br />
          <div style={{ marginTop: '3rem' }}></div>
        </Col>
      </Row>
      <div></div>
    </AvForm>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  bLMappingParamEntity: storeState.bLMappingParam.entity,
  updating: storeState.bLMappingParam.updating,
  updateSuccess: storeState.bLMappingParam.updateSuccess,
  account: storeState.authentication.account,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLMappingParamApprove);

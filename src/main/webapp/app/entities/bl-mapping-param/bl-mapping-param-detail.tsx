import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-mapping-param.reducer';
import { Select } from 'antd';
export interface IBLMappingParamApproveProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLMappingParamApprove = (props: IBLMappingParamApproveProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const { bLMappingParamEntity, updating } = props;
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

  return (
    <AvForm>
      <Row>
        <Col>
          <h2 id="amlApp.bLCustomer.home.createOrEditLabel" data-cy="BLCustomerCreateUpdateHeading">
            Thông tin khai báo nguồn dữ liệu
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
});

const mapDispatchToProps = {
  getEntity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLMappingParamApprove);

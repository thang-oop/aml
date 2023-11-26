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
export interface IBLMappingParamUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLMappingParamUpdate = (props: IBLMappingParamUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const { bLMappingParamEntity, loading, updating } = props;
  const [columName, setColumName] = useState([]);
  const handleClose = () => {
    props.history.push('/bl-mapping-param' + props.location.search);
  };
  const [formErrors, setFormErrors] = useState({});
  const [selectedOptionsPerRow, setSelectedOptionsPerRow] = useState([]);
  const [options, setOptions] = useState([]);
  const [selectedDataRow, setSelectedDataRow] = useState([]);
  const [dataRow, setDataRow] = useState([]);
  const [sourceName, setSourceName] = useState('');
  const [sourcePrefixFix, setSourcePrefixFix] = useState('');

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = values => {
    const mappedData = dataRow.map((rowSelect, index) => {
      const arrayItemSelected = Object.values(rowSelect).toString().split(',');
      let a = '';

      arrayItemSelected.forEach((itemSelected, indexRow) => {
        if (itemSelected !== undefined && itemSelected !== null) {
          a += itemSelected;
          if (indexRow < arrayItemSelected.length - 1) {
            a += ',';
          }
        }
      });
      return `${Object.keys(rowSelect).join()}: ${a}`;
    });

    const entity: IBLMappingParam = {
      id: bLMappingParamEntity.id,
      sourceName,
      sourceFilePrefix: sourcePrefixFix,
      sourceCols: mappedData.join('||'),
      sourceRef: columName.toString(),
      recordStatus: 'Wait for approve',
      createdBy: bLMappingParamEntity.createdBy,
      dateCreated: bLMappingParamEntity.dateCreated,
    };
    props.updateEntity(entity);
  };

  const handleSelectForRow = (values, key) => {
    const updatedSelectedOptions = [...selectedOptionsPerRow];
    // updatedSelectedOptions[rowIndex] = values;
    setSelectedOptionsPerRow(updatedSelectedOptions);

    const arrValue = dataRow.map(item => {
      const keyValue = Object.keys(item).join();
      if (keyValue === key) {
        item[keyValue] = values;
      }
      return item;
    });
  };

  useEffect(() => {
    const sourceCols = (bLMappingParamEntity?.sourceRef || '').split(',');

    setColumName(sourceCols);
    setSourceName(bLMappingParamEntity.sourceName);
    setSourcePrefixFix(bLMappingParamEntity.sourceFilePrefix);
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
    console.warn(columName);
    console.warn(arrOption);

    setOptions(arrOption);

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
            Chỉnh sửa khai báo nguồn dữ liệu
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
                onChange={e => setSourceName(e.target.value)}
                validate={{
                  required: { value: true, errorMessage: 'Nguồn dữ liệu không được trống.' },
                }}
              />
              <AvFeedback>{formErrors['sourceName']}</AvFeedback>
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
                onChange={e => setSourcePrefixFix(e.target.value)}
                validate={{
                  required: { value: true, errorMessage: 'Tiền tố file không được trống.' },
                }}
              />
              <AvFeedback>{formErrors['sourceFilePrefix']}</AvFeedback>
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
          &nbsp;
          <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" onClick={saveEntity}>
            <FontAwesomeIcon icon="save" />
            &nbsp; Cập nhật
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
                          <Select
                            mode="multiple"
                            style={{ width: '100%' }}
                            placeholder="Please select"
                            defaultValue={col[key]}
                            onChange={e => handleSelectForRow(e, key)}
                            options={options}
                          />
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
  loading: storeState.bLMappingParam.loading,
  updating: storeState.bLMappingParam.updating,
  updateSuccess: storeState.bLMappingParam.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLMappingParamUpdate);

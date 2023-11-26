import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import Papa from 'papaparse';
import { Button, Row, Col, Label, Input, FormGroup } from 'reactstrap';
import Dropdown from 'react-dropdown-select';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { getSession } from 'app/shared/reducers/authentication';
import { IRootState } from 'app/shared/reducers';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { createEntity } from './bl-mapping-param.reducer';
import { getParamterByKey } from '../bl-paramter/bl-paramter.reducer';
import { IBLMappingParam } from 'app/shared/model/bl-mapping-param.model';
export interface IBLMappingParamProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BLMappingParam = (props: IBLMappingParamProps) => {
  const allowedExtensions = ['csv'];

  const [data, setData] = useState([]);

  const [colnumName, setColumName] = useState([]);

  const [error, setError] = useState('');

  // It will store the file uploaded by the
  const [file, setFile] = useState('');

  const [selectedOptionsPerRow, setSelectedOptionsPerRow] = useState([]);
  const [templateCols, setTemplateCols] = useState([]);
  const [sourceName, setSourceName] = useState('');
  const [sourcePrefixFix, setSourcePrefixFix] = useState('');
  const [formErrors, setFormErrors] = useState({});

  const [inputClasses, setInputClasses] = useState({
    sourceName: '',
    sourceFilePrefix: '',
  });

  useEffect(() => {
    props.getParamterByKey('FILE_TEMPLATE_FIELD');
  }, []);

  const handleSelectForRow = (values, rowIndex) => {
    const updatedSelectedOptions = [...selectedOptionsPerRow];
    updatedSelectedOptions[rowIndex] = values;
    setSelectedOptionsPerRow(updatedSelectedOptions);
  };

  const handleFileChange = e => {
    setError('');

    // Check if user has entered the file
    if (e.target.files.length) {
      const inputFile = e.target.files[0];

      const fileExtension = inputFile?.type.split('/')[1];
      if (!allowedExtensions.includes(fileExtension)) {
        setError('Please input a csv file');
        return;
      }

      setFile(inputFile);
    }
  };
  const handleParse = () => {
    if (!file) return alert('Enter a valid file');

    const reader = new FileReader();

    reader.onload = ({ target }) => {
      const csv = Papa.parse(target.result, {
        header: true,
      });
      const parsedData = csv?.data;
      const rows = Object.keys(parsedData[0]);

      const columns = Object.values(parsedData[0]);

      setColumName(rows);

      const res = rows.reduce((acc, e, i) => {
        return [...acc, [[e], columns[i]]];
      }, []);
      setData(res);
    };
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    //@ts-ignore
    reader.readAsText(file);
    setTemplateCols(blTemplatesCols.keyValue.split(','));
  };
  const { account } = props;

  const handleValidation = () => {
    const errors = {};

    // Kiểm tra và cập nhật lỗi cho sourceName
    if (!sourceName.trim()) {
      errors['sourceName'] = 'Nguồn dữ liệu không được trống.';
      setInputClasses(prevClasses => ({ ...prevClasses, sourceName: 'has-error' }));
    } else {
      errors['sourceName'] = '';
      setInputClasses(prevClasses => ({ ...prevClasses, sourceName: '' }));
    }

    // Kiểm tra và cập nhật lỗi cho sourceFilePrefix
    if (!sourcePrefixFix.trim()) {
      errors['sourceFilePrefix'] = 'Tiền tố file không được trống.';
      setInputClasses(prevClasses => ({ ...prevClasses, sourceFilePrefix: 'has-error' }));
    } else {
      errors['sourceFilePrefix'] = '';
      setInputClasses(prevClasses => ({ ...prevClasses, sourceFilePrefix: '' }));
    }
    setFormErrors(errors);
    return Object.values(errors).every(error1 => !error1);
  };

  useEffect(() => {
    handleValidation();
  }, [sourceName, sourcePrefixFix]);

  const currentDate = new Date();
  const formattedDate = `${padNumber(currentDate.getDate())}/${padNumber(
    currentDate.getMonth() + 1
  )}/${currentDate.getFullYear()} ${padNumber(currentDate.getHours())}:${padNumber(currentDate.getMinutes())}:${padNumber(
    currentDate.getSeconds()
  )}`;

  function padNumber(number) {
    return number.toString().padStart(2, '0');
  }

  const saveEntity = () => {
    if (handleValidation()) {
      const mappedData = templateCols.map((templateCol, index) => {
        const sourceColValues = selectedOptionsPerRow[index]?.map(option => option.label).join(',');
        return `${templateCol}: ${sourceColValues}`;
      });

      const entity: IBLMappingParam = {
        sourceName,
        sourceFilePrefix: sourcePrefixFix, // Add the actual sourcePrefixFix value
        sourceCols: mappedData.join('||'), // Concatenate mappedData and store in entity
        sourceRef: colnumName.toString(),
        recordStatus: 'Wait for approve',
        createdBy: account.login,
        dateCreated: formattedDate,
      };
      props.createEntity(entity);
    } else {
      toast.error('Please fill in all required fields.');
    }
  };

  const { blTemplatesCols } = props;
  return (
    <div>
      <Row>
        <Col>
          <h2 id="amlApp.bLCustomer.home.createOrEditLabel" data-cy="BLCustomerCreateUpdateHeading">
            Khai báo nguồn dữ liệu
          </h2>
        </Col>
      </Row>
      <Row>
        <Col md="8">
          <AvForm>
            <AvGroup>
              <Label id="keyIdLabel" for="bl-paramter-keyId">
                Nguồn dữ liệu
              </Label>
              <AvField
                id="bl-paramter-sourceName"
                data-cy="keyId"
                type="text"
                name="sourceName"
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
              <AvField
                id="bl-paramter-sourcePrefixFix"
                data-cy="keyId"
                type="text"
                name="sourceFilePrefix"
                onChange={e => setSourcePrefixFix(e.target.value)}
                validate={{
                  required: { value: true, errorMessage: 'Tiền tố file không được trống.' },
                  // Add other validation rules as needed
                }}
              />
              <AvFeedback>{formErrors['sourceFilePrefix']}</AvFeedback>
            </AvGroup>
            <AvGroup>
              <Label id="prefixLabel" for="bl-paramter-keyId">
                File nguồn:
              </Label>
              <Input onChange={handleFileChange} id="exampleFile" name="file" type="file" />
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
          <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" className="mx-auto" onClick={handleParse}>
            <FontAwesomeIcon icon="save" />
            &nbsp; Lấy dữ liệu
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
          <AvForm>
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
                    {templateCols.map((col, j) => (
                      <tr key={j} className="item">
                        <td>{col}</td>
                        <td>
                          <Dropdown
                            multi
                            options={colnumName.map((columnName, index) => ({
                              label: columnName,
                              value: columnName,
                            }))}
                            values={selectedOptionsPerRow[j] || []}
                            onChange={values => handleSelectForRow(values, j)}
                            placeholder={`Select options for Row ${j + 1}`}
                          />
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </AvGroup>
            <br />
            <br />

            <div style={{ marginTop: '3rem' }}></div>
          </AvForm>
        </Col>
      </Row>
      <div></div>
    </div>
  );
};

const mapStateToProps = ({ bLParamter, authentication }: IRootState) => ({
  blTemplatesCols: bLParamter.entity,
  account: authentication.account,
});

const mapDispatchToProps = {
  getParamterByKey,
  createEntity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLMappingParam);

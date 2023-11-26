/* eslint-disable no-console */
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvInput, AvFeedback } from 'availity-reactstrap-validation';
import Axios from 'axios';
import React from 'react';
import { Link, Redirect } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Button, Col, Label, Row, Table } from 'reactstrap';
import * as XLSX from 'xlsx';
import { connect } from 'react-redux';
import { IRootState } from 'app/shared/reducers';
import { useState, useEffect } from 'react';
import { getSession } from 'app/shared/reducers/authentication';
import { getProfile } from 'app/shared/reducers/application-profile';
import { getListSourceData } from 'app/entities/bl-upload-file/bl-upload-file.reducer'

export interface IBatchCusServRegUploadProps extends StateProps, DispatchProps { }

export interface IBatchCusServRegUploadState {
  selectedFile: any;
  previewData: any;
  totalRowError: number;
  description: string;
  selectValue: string;
  sms: string;
  email: string;
  fileName: string;
  redirect: boolean;
  totalClick: number;
}

class BatchCusServRegUpload extends React.Component<IBatchCusServRegUploadProps, IBatchCusServRegUploadState> {
  state = {
    selectedFile: {},
    previewData: [],
    totalRowError: 0,
    description: '',
    selectValue: '',
    fileName: '',
    sms: '',
    email: '',
    redirect: false,
    totalClick: 0,
  };

  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.getListSourceData();
    this.props.getSession();
    this.props.getProfile();


  }

  componentDidUpdate(prevProps: Readonly<IBatchCusServRegUploadProps>, prevState: Readonly<IBatchCusServRegUploadState>, snapshot?: any): void {
    if (this.props.listSourceData !== prevProps.listSourceData && this.props.listSourceData.length > 0) {
      this.setState({
        ...this.state,
        selectValue: this.props.listSourceData[0]?.id
      })
    }
  }

  render() {
    const ibGroups = [];



    const clear = () => {

    };

    const handleSelectChange = event => {
      const selectedValue = event.target.value;
      this.setState({
        selectValue: selectedValue,
      });
    };

    const checkFormart = dataParse => {
      return dataParse[0][0] === 'FULL.NAME.BL' && dataParse[0][1] === 'FIRST.NAME' && dataParse[0][2] === 'LAST.NAME';
    };

    const onFileSelect = event => {
      const files = event.target.files;
      if (files === null || files.length === 0) {
        toast.error('Vui lòng chọn file excel cần upload');
        return;
      }

      const f = files[0];

      const fileExt = f.name.split('.').pop();

      if (fileExt !== 'xlsx' && fileExt !== 'xls') {
        toast.error('File upload không đúng định dạng (xls, xlsx)');
        return;
      }

      const today = new Date();

      this.setState({
        fileName:
          f.name.split('.')[0] +
          '_' +
          today.getDate() +
          '-' +
          ((today.getMonth() + 1).toString().length === 1
            ? '0' + (parseInt(today.getMonth().toString(), 10) + 1)
            : parseInt(today.getMonth().toString(), 10) + 1) +
          '-' +
          today.getFullYear() +
          '_' +
          today.getHours() +
          '-' +
          today.getMinutes() +
          '-' +
          today.getSeconds() +
          '.' +
          f.name.split('.')[1],
      });

      const reader = new FileReader();
      const list = [];

      reader.onload = e => {
        const data = reader.result;
        const readedData = XLSX.read(data, { type: 'binary' });
        const wsname = readedData.SheetNames[0];
        const ws = readedData.Sheets[wsname];

        const dataParse = XLSX.utils.sheet_to_json(ws, { header: 1 });

        if (dataParse.length === 0) {
          toast.error('File không chứa nội dung');
          return;
        }

        if (checkFormart(dataParse)) {
          for (let i = 1; i < dataParse.length; i++) {
            const element = dataParse[i] as any;

            if (element !== undefined && element.length === 0) {
              continue;
            }

            const row = {
              index: i,
              fullName: element[0],
              firstName: element[1],
              lastName: element[2],
              otherName1: element[3],
              otherName2: element[4],
              otherName3: element[5],
              positionBL: element[6],
              dateOfBirthBl: element[7],
              countryBL: element[8],
              countryBL2: element[9],
              legalIdTypeBL1: element[10],
              legalIdNumberBL1: element[11],
              legalIdTypeBL2: element[12],
              legalIdNumberBL2: element[13],
              otherInfLegal1: element[14],
              otherInfLegal2: element[15],
              addressBl1: element[17],
              addressBl2: element[18],
              addressNowBl1: element[19],
              addressNowBl2: element[20],
              source: element[21],
            };
            list.push(row);
          }
        } else {
          toast.error('File không đúng Formart');
        }

        this.setState({
          selectedFile: f,
          previewData: list,
        });
      };

      reader.readAsBinaryString(f);
      event.preventDefault();
    };


    const fileUpload = event => {
      this.setState({
        totalClick: 1,
      });

      event.preventDefault();

      const apiUrl = 'api/bl-upload/upload';

      const uploadData = [];

      this.state.previewData.map(row => {
        if (row.isError === false) {
          uploadData.push(row);
        }
      });
      const formData = new FormData();
      formData.append('file', this.state.selectedFile as any);
      formData.append(
        'datafile',
        new Blob(
          [
            JSON.stringify({
              cifItems: uploadData,
              fileName: this.state.fileName,
              typefile: this.state.selectValue,
            }),
          ],
          {
            type: 'application/json',
          }
        )
      );

      // Prepare upload data
      Axios.post(apiUrl, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })
        .then(response => {
          toast.success('Upload dữ liệu thành công !');
          this.setState({ redirect: true });
        })
        .catch(error => {
          toast.error('Upload dữ liệu thất bại !');
        });
      event.preventDefault();
    };

    return (
      <div>
        <div>
          <h2>Hệ thống AML upload blacklist file</h2>
        </div>

        <div className="clearfix uploader-wrapper">
          <AvForm id="file-upload-form" className="uploader clearfix" onSubmit={fileUpload}>
            <Row>
              {this.state.previewData.length === 0 ? (
                <Col md="5" lg="4">
                  <Label
                    for="file-upload"
                    id="file-drag"
                    className="clearfix margin-below"
                    style={{ maxWidth: 'auto', marginLeft: '450px' }}
                  >
                    <input id="file-upload" placeholder="checkbox" type="file" name="fileUpload" onChange={onFileSelect} />
                    <img id="file-image" src="#" alt="Preview" className="hidden" />
                    <div id="start">
                      <FontAwesomeIcon icon="upload" />
                      <div>Chọn file hoặc kéo vào đây để upload</div>
                      <div id="notexcel" className="hidden">
                        Vui lòng chỉ update file excel
                      </div>
                      <span id="file-upload-btn" className="btn btn-primary btn-select">
                        Chọn file
                      </span>
                    </div>
                    <div id="response" className="hidden">
                      <div id="messages"></div>
                      <progress className="progress" id="file-progress" value="0">
                        <span>0</span>%
                      </progress>
                    </div>
                  </Label>
                </Col>
              ) : (
                <Col md="5" lg="4">
                  <Label className="clearfix blur margin-below" style={{ maxWidth: 'auto' }}>
                    <img id="file-image" alt="Preview" className="hidden" />
                    <div id="start">
                      <FontAwesomeIcon icon="upload" />
                      <div>Chọn file hoặc kéo vào đây để upload</div>
                      <div id="notexcel" className="hidden">
                        Vui lòng chỉ update file excel
                      </div>
                      <span id="file-upload-btn" className="btn btn-primary btn-select">
                        Chọn file
                      </span>
                    </div>
                    <div id="response" className="hidden">
                      <div id="messages"></div>
                      <progress className="progress" id="file-progress" value="0">
                        <span>0</span>%
                      </progress>
                    </div>
                  </Label>
                </Col>
              )}
            </Row>
            <div className="col-lg-2" style={{ marginLeft: '600px' }}>
              <div className="form-default">
                <AvInput
                  type="select"
                  name="select"
                  id="exampleSelect"
                  value={this.state.selectValue}
                  onChange={handleSelectChange}
                >
                  {this.props.listSourceData.map(item => (
                    <option key={item.id} value={item.id}>
                      {item.sourceName}
                    </option>
                  ))}
                </AvInput>
              </div>
            </div>
            <br />

            {this.state.totalRowError > 0 ? (
              <p className="color-error">Những dòng bị bôi đỏ là dữ liệu nhập sai yêu cầu, lỗi format</p>
            ) : (
              ''
            )}
            <div className="view-table">
              <Label className="margin-below create-cif" style={{ maxHeight: '440.5px', overflow: 'auto' }}>
                <Table bordered>
                  <thead>
                    <th>STT</th>
                    <th>FULL.NAME.BL</th>
                    <th>FIRST.NAME</th>
                    <th>LAST.NAME</th>
                    <th>OTHER.NAME.BL.1</th>
                    <th>OTHER.NAME.BL.2</th>
                    <th>OTHER.NAME.BL.3</th>
                    <th>POSITION.BL</th>
                    <th>DATE.OF.BIRTH.BL</th>
                    <th>COUNTRY.BL</th>
                    <th>COUNTRY.BL.2</th>
                    <th>LEGAL.ID.TYPE.BL.1</th>
                    <th>LEGAL.ID.NUMBER.1</th>
                    <th>LEGAL.ID.TYPE.BL.2</th>
                    <th>LEGAL.ID.NUMBER.2</th>
                    <th>OTHER.INF.LEGAL.1</th>
                    <th>OTHER.INF.LEGAL.2</th>
                    <th>ADDRESS.BL.1</th>
                    <th>ADDRESS.BL.2</th>
                    <th>ADDRESS.NOW.BL.1</th>
                    <th>ADDRESS.NOW.BL.2</th>
                    <th>SOURCE</th>
                  </thead>
                  <tbody>
                    {this.state.previewData.length > 0 ? (
                      this.state.previewData.map(element => (
                        <tr key={element.index} className={element.isError ? 'table-danger' : ''}>
                          <td>{element.index}</td>
                          <td>{element.fullName}</td>
                          <td>{element.firstName}</td>
                          <td>{element.lastName}</td>
                          <td>{element.otherName1}</td>
                          <td>{element.otherName2}</td>
                          <td>{element.otherName3}</td>
                          <td>{element.positionBL}</td>
                          <td>{element.dateOfBirthBl}</td>
                          <td>{element.countryBL}</td>
                          <td>{element.countryBL2}</td>
                          <td>{element.legalIdTypeBL1}</td>
                          <td>{element.legalIdNumberBL1}</td>
                          <td>{element.legalIdTypeBL2}</td>
                          <td>{element.legalIdNumberBL2}</td>
                          <td>{element.otherInfLegal1}</td>
                          <td>{element.otherInfLegal2}</td>
                          <td>{element.addressBl1}</td>
                          <td>{element.addressBl2}</td>
                          <td>{element.addressNowBl1}</td>
                          <td>{element.addressNowBl2}</td>
                          <td>{element.source}</td>
                        </tr>
                      ))
                    ) : (
                      <tr>
                        <td colSpan={31} className="text-center pad-1rem">
                          Dữ liệu xem trước khi upload
                        </td>
                      </tr>
                    )}
                  </tbody>
                </Table>
              </Label>
            </div>

            <Row>
              <div className="col col-md-12 no-padding text-right">
                <div className="form-inline float-right input-group-sm">
                  <button
                    type="submit"
                    className="btn btn-success btn-sm mr-6"
                    disabled={this.state.previewData.length === 0 || this.state.totalRowError !== 0 || this.state.totalClick === 1}
                  >
                    <FontAwesomeIcon icon="upload" /> Xác nhận và upload
                  </button>
                  <button type="button" className="btn btn-danger btn-sm" onClick={clear} disabled={this.state.previewData.length === 0}>
                    <FontAwesomeIcon icon="times" /> Chọn lại
                  </button>
                </div>
              </div>
            </Row>
          </AvForm>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  account: storeState.authentication.account,
  listSourceData: storeState.bLUploadFile.listSourceData
});

const mapDispatchToProps = {
  getSession,
  getProfile,
  getListSourceData
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BatchCusServRegUpload);

import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, Table } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { toast } from 'react-toastify';
import axios from 'axios';
import Axios from 'axios';

import * as XLSX from 'xlsx';
import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bl-upload-file.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBLUploadFileDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLUploadFileDetail = (props: IBLUploadFileDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const [listData, setListData] = useState([]);

  const checkFormart = dataParse => {
    return dataParse[0][0] === 'FULL.NAME.BL' && dataParse[0][1] === 'FIRST.NAME' && dataParse[0][2] === 'LAST.NAME';
  };
  const reader = new FileReader();
  const list = [];

  const ComfirmUploadFile = event => {
    const apiUrl = 'api/bl-upload/comfirm-upload';

    const fileName = bLUploadFileEntity.fileName;

    Axios.post(apiUrl, fileName, {})
      .then(response => {
        toast.success('Comfirm file thành công !');
      })
      .catch(error => {
        toast.error('Insert vào database thất bại !');
      });
  };

  const readFileContent = () => {
    // Đường dẫn đến tệp bạn muốn đọc
    const filePath = './../../../content/AML_blacklist/' + bLUploadFileEntity.fileName + '.xlsx';

    // console.log("file Path " + filePath);

    axios
      .get(filePath, {
        responseType: 'arraybuffer',
      })
      .then(response => {
        const arrayBuffer = response.data;
        const dataArr = new Uint8Array(arrayBuffer);
        const workbook = XLSX.read(dataArr, { type: 'array' });

        // Lấy danh sách các sheet trong tệp XLSX
        const sheetNames = workbook.SheetNames;

        // Lấy dữ liệu từ sheet đầu tiên (có thể sửa lại để chọn sheet khác)
        const sheet = workbook.Sheets[sheetNames[0]];

        // Chuyển đổi dữ liệu từ sheet sang mảng JSON
        const dataParse = XLSX.utils.sheet_to_json(sheet, { defval: '', header: 1 });

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
          setListData(list);
        } else {
          toast.error('File không đúng Formart');
        }
      })
      .catch(error => {
        console.error('Lỗi khi tải tệp:', error);
      });
  };

  const { bLUploadFileEntity } = props;
  return (
    <Row>
      <Col md="12">
        <h2 data-cy="bLUploadFileDetailsHeading">Thông tin file đã upload</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID: {bLUploadFileEntity.id}</span>
          </dt>
          <dt>
            <span id="fileName">File Name: {bLUploadFileEntity.fileName}</span>
          </dt>
          <dt>
            <span id="systemFileName">System File Name : {bLUploadFileEntity.systemFileName}</span>
          </dt>
          <dt>
            <span id="description">Description: {bLUploadFileEntity.description}</span>
          </dt>

          <dt>
            <span id="tagetCompany">Taget Company: {bLUploadFileEntity.tagetCompany}</span>
          </dt>

          <dt>
            <span id="serviceStatus">Service Status: {bLUploadFileEntity.serviceStatus}</span>
          </dt>
          <dt>
            <span id="fileSize">File Size: {bLUploadFileEntity.fileSize}</span>
          </dt>
          <dt>
            <span id="recordStatus">Record Status:{bLUploadFileEntity.recordStatus}</span>
          </dt>
          <dt>
            <span id="uploadBy">Upload By : {bLUploadFileEntity.uploadBy}</span>
          </dt>
          <dt>
            <span id="createdBy">Created By: {bLUploadFileEntity.createdBy}</span>
          </dt>
          <dt>
            <span id="dateCreated">Date Created: {bLUploadFileEntity.dateCreated}</span>
          </dt>
        </dl>
        <div className="clearfix uploader-wrapper">
          <div className="uploader clearfix">
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
                    {listData.length > 0 ? (
                      listData.map(element => (
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
                  <button type="submit" className="btn btn-success btn-sm mr-6" disabled={listData.length === 0}>
                    <FontAwesomeIcon icon="upload" /> Xác nhận và upload
                  </button>
                  {/* <button type="button" className="btn btn-danger btn-sm" onClick={() => setListData([])} disabled={listData.length === 0}><FontAwesomeIcon icon="times" /> Chọn lại</button> */}
                </div>
              </div>
            </Row>
          </div>
        </div>
        <Button replace color="info" onClick={() => readFileContent()}>
          <span className="d-none d-md-inline">Xem chi tiết file</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bl-upload-file/${bLUploadFileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
        &nbsp;
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bLUploadFile }: IRootState) => ({
  bLUploadFileEntity: bLUploadFile.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLUploadFileDetail);

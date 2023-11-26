import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import Multiselect from 'multiselect-react-dropdown';
import Select from 'react-select';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bl-rule.reducer';
import { getParamterByKey } from '../bl-paramter/bl-paramter.reducer';
import { getListSourceData } from '../bl-mapping-param/bl-mapping-param.reducer';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBLRuleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLRuleUpdate = (props: IBLRuleUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { bLRuleEntity, paramEntity, listSourceData, loading, updating } = props;

  const [customerType, setCustomerType] = useState([]);

  const [selectedSourceData, setSelectedSourceData] = useState([]);

  const [selectedCustomerType, setSelectedCustomerType] = useState([]);

  const handleSourceDataChange = options => {
    setSelectedSourceData(options);
  };

  const handleCustomerTypeChange = options => {
    setSelectedCustomerType(options);
  };

  const handleClose = () => {
    props.history.push('/bl-rule');
  };

  useEffect(() => {
    props.getListSourceData();
    props.getParamterByKey('CUSTOMER_TYPE');
    if (paramEntity.keyValue != null) {
      setCustomerType(paramEntity.keyValue.split(','));
    }
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

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...bLRuleEntity,
        ...values,
      };
      const sourceIds = selectedSourceData.map(opt => opt.value);
      entity.sourceIds = sourceIds.reduce((acc, item) => `${acc}${item},`, '');
      const customerTypes = selectedCustomerType.map(opt => opt.value);
      entity.customerType = customerTypes.reduce((acc, item) => `${acc}${item},`, '');
      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row>
        <Col md="8">
          <h2 id="amlApp.bLRule.home.createOrEditLabel" data-cy="BLRuleCreateUpdateHeading">
            Thêm, sửa rule & policy
          </h2>
        </Col>
      </Row>
      <Row>
        <Col md="8">
          {loading ? (
            <p>Đang tải...</p>
          ) : (
            <AvForm model={isNew ? {} : bLRuleEntity} onSubmit={saveEntity}>
              <AvGroup>
                <Label id="nameLabel" for="bl-rule-name">
                  Tên rule
                </Label>
                <AvField id="bl-rule-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="bl-rule-description">
                  Mô tả
                </Label>
                <AvField id="bl-rule-description" data-cy="description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="sourceIdsLabel" for="bl-rule-sourceIds">
                  Nguồn dữ liệu
                </Label>
                <Select
                  data-cy="sourceIds"
                  isMulti={true}
                  onChange={handleSourceDataChange}
                  options={listSourceData.map(data => {
                    return {
                      label: data.sourceName,
                      value: data.id,
                    };
                  })}
                />
              </AvGroup>
              <AvGroup>
                <Label id="customerTypeLabel" for="bl-rule-customerType">
                  Loại khách hàng
                </Label>
                <Select
                  data-cy="customerType"
                  isMulti={true}
                  onChange={handleCustomerTypeChange}
                  options={customerType.map(data => {
                    return {
                      label: data,
                      value: data,
                    };
                  })}
                />
              </AvGroup>
              <AvGroup>
                <Label id="scoreMinimumLabel" for="bl-rule-scoreMinimum">
                  Điểm tối thiểu
                </Label>
                <AvField id="bl-rule-scoreMinimum" data-cy="scoreMinimum" type="string" className="form-control" name="scoreMinimum" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bl-rule" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Quay lại</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Cập nhật
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  bLRuleEntity: storeState.bLRule.entity,
  loading: storeState.bLRule.loading,
  updating: storeState.bLRule.updating,
  updateSuccess: storeState.bLRule.updateSuccess,
  paramEntity: storeState.bLParamter.entity,
  listSourceData: storeState.bLMappingParam.entities,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
  getParamterByKey,
  getListSourceData,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLRuleUpdate);

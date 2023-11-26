import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import Select from 'react-select';

import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bl-condition.reducer';
import { getParamterByKey } from '../bl-paramter/bl-paramter.reducer';
import { getRuleEntity } from '../bl-rule/bl-rule.reducer';

import { IBLCondition } from 'app/shared/model/bl-condition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBLConditionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BLConditionUpdate = (props: IBLConditionUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const [blacklistFld, setBlacklistFld] = useState([]);

  const [customerfld, setCustomerFld] = useState([]);

  const [selectedSourceData, setSelectedSourceData] = useState([]);

  const [selectedCustomerType, setSelectedCustomerType] = useState([]);

  const { conditionEntity, paramEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/bl-condition');
  };

  const handleSourceDataChange = options => {
    setSelectedSourceData(options);
  };

  const handleCustomerTypeChange = options => {
    setSelectedCustomerType(options);
  };

  useEffect(() => {
    props.getParamterByKey('BLACK_LIST_FIELD');
    if (paramEntity.keyValue != null) {
      setBlacklistFld(paramEntity.keyValue.split(','));
    }
    props.getParamterByKey('CUSTOMER_FIELD');
    if (paramEntity.keyValue != null) {
      setCustomerFld(paramEntity.keyValue.split(','));
    }
    props.getRuleEntity('');
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
        ...conditionEntity,
        ...values,
      };

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
          <h2 id="amlApp.bLCondition.home.createOrEditLabel" data-cy="BLConditionCreateUpdateHeading">
            Thêm/sửa điều kiện
          </h2>
        </Col>
      </Row>
      <Row>
        <Col md="8">
          {loading ? (
            <p>Đang tải...</p>
          ) : (
            <AvForm model={isNew ? {} : conditionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="bl-condition-id">ID</Label>
                  <AvInput id="bl-condition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="ruleIdLabel" for="bl-condition-ruleId">
                  Rule Id
                </Label>
                <AvField id="bl-condition-ruleId" data-cy="ruleId" type="string" className="form-control" name="ruleId" />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="bl-condition-name">
                  Điều kiện
                </Label>
                <AvField id="bl-condition-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="bl-condition-description">
                  Diễn giải
                </Label>
                <AvField id="bl-condition-description" data-cy="description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="blackListFldsLabel" for="bl-condition-blackListFlds">
                  Danh sách trường (Blacklist)
                </Label>
                <Select
                  data-cy="blackListFlds"
                  isMulti={true}
                  onChange={handleSourceDataChange}
                  options={blacklistFld.map(data => {
                    return {
                      label: data,
                      value: data,
                    };
                  })}
                />
              </AvGroup>
              <AvGroup>
                <Label id="customerFldsLabel" for="bl-condition-customerFlds">
                  Danh sách trường (Customer)
                </Label>
                <Select
                  data-cy="customerFlds"
                  isMulti={true}
                  onChange={handleSourceDataChange}
                  options={customerfld.map(data => {
                    return {
                      label: data,
                      value: data,
                    };
                  })}
                />
              </AvGroup>
              <AvGroup>
                <Label id="weightPointLabel" for="bl-condition-weightPoint">
                  Trọng số
                </Label>
                <AvField id="bl-condition-weightPoint" data-cy="weightPoint" type="string" className="form-control" name="weightPoint" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bl-condition" replace color="info">
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
  conditionEntity: storeState.bLCondition.entity,
  ruleEntity: storeState.bLRule.entity,
  paramEntity: storeState.bLParamter.entity,
  loading: storeState.bLCondition.loading,
  updating: storeState.bLCondition.updating,
  updateSuccess: storeState.bLCondition.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
  getParamterByKey,
  getRuleEntity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BLConditionUpdate);

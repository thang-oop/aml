import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavDropdown } from './menu-components';

const adminMenuItems = (
  <>
    <MenuItem icon="tachometer-alt" to="/bl-paramter">
      Tham số hệ thống
    </MenuItem>
    <MenuItem icon="heart" to="/jhi-user-authority">
      Phân quyền hệ thống
    </MenuItem>
    <MenuItem icon="cogs" to="/bl-mapping-param">
      Khai báo nguồn dữ liệu
    </MenuItem>
    <MenuItem icon="tasks" to="/bl-rule">
      Quản lý rules và policies
    </MenuItem>
  </>
);

export const AdminMenu = ({ showOpenAPI }) => (
  <NavDropdown icon="users-cog" name="Hệ thống" id="admin-menu" data-cy="adminMenu">
    {adminMenuItems}
  </NavDropdown>
);

export default AdminMenu;

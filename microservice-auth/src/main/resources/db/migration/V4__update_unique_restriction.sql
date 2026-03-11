-- 2. RESTRICCIONES DE UNICIDAD (Evitar duplicados)
-- Esto garantiza que no existan filas redundantes en las tablas de unión
ALTER TABLE users_roles
    ADD CONSTRAINT uk_user_role UNIQUE (user_id, role_id);

ALTER TABLE roles_permissions
    ADD CONSTRAINT uk_role_permission UNIQUE (role_id, permission_id);

ALTER TABLE roles_menus
    ADD CONSTRAINT uk_role_menu UNIQUE (role_id, menu_id);
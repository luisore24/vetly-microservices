-- 1. CORRECCIÓN DE TIPOS DE DATOS Y LLAVES FORÁNEAS (BIGINT)
-- Cambiamos integer a bigint para coincidir con las tablas maestras
ALTER TABLE users_roles
    ALTER COLUMN user_id TYPE bigint,
    ALTER COLUMN role_id TYPE bigint;

ALTER TABLE roles_permissions
    ALTER COLUMN role_id TYPE bigint,
    ALTER COLUMN permission_id TYPE bigint;

ALTER TABLE roles_menus
    ALTER COLUMN role_id TYPE bigint,
    ALTER COLUMN menu_id TYPE bigint;

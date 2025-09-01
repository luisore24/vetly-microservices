
--INSERT PARA TABLA status
INSERT INTO status (id,description, comment, created_at, created_by, updated_at, updated_by)
VALUES (1, 'ACTIVO', 'ACTIVO', NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA users | clave encriptada: admin
INSERT INTO users (id, username,password,name,lastname,email,phone,address,status_id,is_enabled,account_no_expired,account_no_locked,credential_no_expired,last_login,last_login_ip,created_at,created_by,
  updated_at,updated_by
) VALUES (
  1, 'admin','$2a$10$s6DtAt761jw2Ws97r4EOu..WNwpxDcJEFRK9vgeLwBVKpsGtYS1pK','Juan','Pérez','admin@example.com','123456789','Calle Falsa 123',1,TRUE,TRUE,TRUE,TRUE,NOW(),'192.168.1.100',NOW(),'SYSTEM',NOW(),'SYSTEM');


--INSERT PARA TABLA roles
INSERT INTO roles (id, description, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 'ADMIN', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(2, 'USER', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA permissions
INSERT INTO permissions (id, description, comment, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 'READ', 'Permiso para leer', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(2, 'WRITE', 'Permiso para escribir', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA roles_permissions
INSERT INTO roles_permissions (id, role_id, permission_id, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 1, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin tiene permiso de lectura
(2, 1, 2, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin tiene permiso de escritura
(3, 2, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');  -- User tiene permiso solo de lectura


--INSERT PARA TABLA menus
INSERT INTO menus (id, description, status_id, icon, route, created_at, created_by, updated_at, updated_by)
VALUES
(1, 'Dashboard', 1, 'dashboard-icon', '/dashboard', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(2, 'Settings', 1, 'settings-icon', '/settings', NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA roles_menus
INSERT INTO roles_menus (id, role_id, menu_id, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 1, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin puede ver Dashboard
(2, 1, 2, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin puede ver Settings
(3, 2, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');  -- User puede ver Dashboard


--INSERT PARA TABLA users_roles
INSERT INTO users_roles (id, user_id, role_id, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 1, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');  -- Usuario 1 tiene rol Admin






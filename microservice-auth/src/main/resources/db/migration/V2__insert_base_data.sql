
--INSERT PARA TABLA status
INSERT INTO status (description, comment, is_allowed, created_at, created_by, updated_at, updated_by)
VALUES ('ACTIVO', 'ACTIVO', TRUE, NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA users | clave encriptada: admin
INSERT INTO users (username,password,name,lastname,email,phone,address,status_id,is_enabled,account_no_expired,account_no_locked,credential_no_expired,created_at,created_by,
  updated_at,updated_by, is_deleted)
  VALUES ('admin','$2a$10$s6DtAt761jw2Ws97r4EOu..WNwpxDcJEFRK9vgeLwBVKpsGtYS1pK','Juan','Pérez','admin@example.com','123456789','Calle Falsa 123',1,TRUE,TRUE,TRUE,TRUE,NOW(),'SYSTEM',NOW(),'SYSTEM', FALSE);


--INSERT PARA TABLA roles
INSERT INTO roles (description, status_id, created_at, created_by, updated_at, updated_by)
VALUES
('ADMIN', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
('USER', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA permissions
INSERT INTO permissions (description, comment, status_id, created_at, created_by, updated_at, updated_by)
VALUES
('READ', 'Permiso para leer', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
('WRITE', 'Permiso para escribir', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA roles_permissions
INSERT INTO roles_permissions (role_id, permission_id, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin tiene permiso de lectura
(1, 2, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin tiene permiso de escritura
(2, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');  -- User tiene permiso solo de lectura


--INSERT PARA TABLA menus
INSERT INTO menus (description, status_id, icon, route, created_at, created_by, updated_at, updated_by)
VALUES
('Dashboard', 1, 'dashboard-icon', '/dashboard', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
('Settings', 1, 'settings-icon', '/settings', NOW(), 'SYSTEM', NOW(), 'SYSTEM');


--INSERT PARA TABLA roles_menus
INSERT INTO roles_menus (role_id, menu_id, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin puede ver Dashboard
(1, 2, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM'),  -- Admin puede ver Settings
(2, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');  -- User puede ver Dashboard


--INSERT PARA TABLA users_roles
INSERT INTO users_roles (user_id, role_id, status_id, created_at, created_by, updated_at, updated_by)
VALUES
(1, 1, 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');  -- Usuario 1 tiene rol Admin






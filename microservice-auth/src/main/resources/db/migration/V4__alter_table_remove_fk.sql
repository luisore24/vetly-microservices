--Delete relationship between user and audit_login and delete column un audit_login table.

--Delete Fk

ALTER TABLE audit_login
DROP CONSTRAINT audit_login_user_id_fkey;

--delete column

ALTER TABLE audit_login
DROP COLUMN user_id;
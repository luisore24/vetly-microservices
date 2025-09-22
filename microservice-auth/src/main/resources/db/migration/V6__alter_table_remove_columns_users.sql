--Delete columns from table users

--delete column last login
ALTER TABLE users
DROP COLUMN last_login;

--delete column last ip
ALTER TABLE users
DROP COLUMN last_login_ip;
# setup.sql
CREATE DATABASE IF NOT EXISTS `main_vault`;
CREATE DATABASE IF NOT EXISTS `main_vault_test`;

CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'local';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';

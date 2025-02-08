-- Create the database
CREATE DATABASE IF NOT EXISTS epl;

-- Create the user
CREATE USER IF NOT EXISTS 'test_user'@'%' IDENTIFIED BY 'superSecretPassword!123';

-- Grant all privileges on the epl database to the user
GRANT ALL PRIVILEGES ON epl.* TO 'test_user'@'%' WITH GRANT OPTION;

-- Flush privileges to ensure that the changes take effect
FLUSH PRIVILEGES;

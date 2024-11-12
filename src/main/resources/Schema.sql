-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS library_system;

-- Use the database
USE library_system;

-- Create the CustomUser table if it does not exist
CREATE TABLE IF NOT EXISTS CustomUser (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL, -- Assuming you will store hashed passwords
    userRole ENUM('ADMIN', 'USER') NOT NULL
);

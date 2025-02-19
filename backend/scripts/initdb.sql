CREATE DATABASE IF NOT EXISTS dbUsuario;

CREATE TABLE Usuario (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          dni VARCHAR(20) UNIQUE NOT NULL,
                          email VARCHAR(255) ,
                          password VARCHAR(255),
                          nombre VARCHAR(100),
                          phone VARCHAR(20),
                          token VARCHAR(255)
);

INSERT INTO Usuario (id, dni, email, password,nombre, phone, token) VALUES (1, '12345678A', 'juan.perez@example.com', '$2a$10$VR1jO9Od4XFG21R5mQv5DeTmOCuPcOH5zqqpYF5FC3bk6DJ/vyAR6', 'Juan Pérez', '600123456', ''),
                                                                            (2, '87654321B', 'ana.gomez@example.com', '$2a$10$VR1jO9Od4XFG21R5mQv5DeTmOCuPcOH5zqqpYF5FC3bk6DJ/vyAR6', 'Ana Gómez', '611654987', ''),
                                                                            (3, '11223344C', 'carlos.lopez@example.com', '$2a$10$VR1jO9Od4XFG21R5mQv5DeTmOCuPcOH5zqqpYF5FC3bk6DJ/vyAR6', 'Carlos López', '622987654', '');


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

CREATE TABLE IF NOT EXISTS Monumento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idMonu VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    ciudad VARCHAR(40) NOT NULL,
    fecha VARCHAR(40) NOT NULL,
    descripcion VARCHAR(40) NOT NULL,
    imagen VARCHAR(40) NULL,
    descripcionPlus VARCHAR(40) NOT NULL
);

INSERT INTO Monumento (idMonu, nombre, ciudad, fecha, descripcion, imagen, descripcionPlus) VALUES
('M001', 'Alhambra', 'Granada', '1238', 'Palacio nazarí', 'alhambra.jpg', 'Patrimonio de la Humanidad'),
('M002', 'Sagrada Familia', 'Barcelona', '1882', 'Templo de Gaudí', 'sagrada.jpg', 'Obra maestra inacabada'),
('M003', 'Mezquita', 'Córdoba', '785', 'Templo islámico', 'mezquita.jpg', 'Fusión cultural');

INSERT INTO `Usuario` (`id`, `dni`, `email`, `password`, `nombre`, `phone`, `token`) VALUES
(4, '1239899L', 'copado@gmail.com', 'b16b03c9dbda7e1bae9f057a8fbcbe482bda4d7ec9a6d31a4e8bb26b99537d79', 'copado', '21242323', ''),
(5, '242434343N', 'miguelon@gmail.com', 'e87687e2db8db437ae1eba7809ca17331b7b510977f8acfa0244fa485537c2a5', 'miguelon', '243434343', ''),
(6, '53434343', 'santi@gmail.com', '324ca5355e9d7d5f60fb23b379f5bad7d4a12013a8b89b46ec2392c3021d3a27', 'santi', '343543434', '');

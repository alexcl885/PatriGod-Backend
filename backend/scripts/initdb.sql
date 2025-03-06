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
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(40) NOT NULL,
    fecha VARCHAR(40) NOT NULL,
    descripcion VARCHAR(40) NOT NULL,
    imagen VARCHAR(500) NULL,
    descripcionPlus VARCHAR(500) NOT NULL,
    idUsuario VARCHAR(20)
);

INSERT INTO `Monumento` (`id`, `idMonu`, `nombre`, `ciudad`, `fecha`, `descripcion`, `imagen`, `descripcionPlus`, `idUsuario`) VALUES
(1, 'M000', 'Sacra Capilla del Salvador', 'Úbeda', '1536', 'Templo funerario renacentista', 'https://upload.wikimedia.org/wikipedia/commons/e/e8/Ubeda_-_Capilla_del_Salvador_42.jpg', 'Máximo exponente del Renacimiento en España','123456'),
(2, 'M001', 'Santa María de los Reales Alcázares', 'Úbeda, JaénFFF', '1233', 'Iglesia gótica y renacentista', 'https://imgs.search.brave.com/sicVDBkconPOvYM9pP7qIVu7oCacNGAFxU6oyKIPyx0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvY29tbW9ucy8z/LzNlLzIwMDItMTAt/MjZfMTEtMTVfQW5k/YWx1c2llbixfTGlz/c2Fib25fMTIyXyVD/MyU5QWJlZGEuanBn', 'http://localhost:8085/images/M001/https://imgs.search.brave.com/sicVDBkconPOvYM9pP7qIVu7oCacNGAFxU6oyKIPyx0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvY29tbW9ucy8z/LzNlLzIwMDItMTAt/MjZfMTEtMTVfQW5k/YWx1c2llbixfTGlz/c2Fib25fMTIyXyVD/MyU5QWJlZGEuanBn','123456'),
(3, 'M002', 'Salvador', 'Ubedarrrr', '1147', 'Catedral renacentista', 'https://imgs.search.brave.com/SgCIqDaMR_5xIyu46IvZRsB5HsnBijPcHjKCeOjmIYA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9waG90/bzYyMHg0MDAubW5z/dGF0aWMuY29tL2Iw/ZGQ1OTgzNDY3ZGU1/MmQ4YWNlODI1Mjk2/OTM5NTk0L2NhdGVk/cmFsLWRlLWJhZXph/LmpwZw', 'http://localhost:8085/images/M002/https://imgs.search.brave.com/SgCIqDaMR_5xIyu46IvZRsB5HsnBijPcHjKCeOjmIYA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9waG90/bzYyMHg0MDAubW5z/dGF0aWMuY29tL2Iw/ZGQ1OTgzNDY3ZGU1/MmQ4YWNlODI1Mjk2/OTM5NTk0L2NhdGVk/cmFsLWRlLWJhZXph/LmpwZw','123456'),
(4, 'M003', 'Fuente de Santa María', 'Baeza, Jaén', '1564', 'Fuente renacentista', 'https://media-cdn.tripadvisor.com/media/photo-s/0e/c8/f4/07/construida-por-el-maestro.jpg', 'Símbolo del esplendor arquitectónico de Baeza', '123456'),
(5, 'M004', 'Plaza Mayor de Salamanca', 'Salamanca', '1729 - 1756888', 'Plaza barrocaaaaaaaaaaaaaaaaaaaa', 'https://imgs.search.brave.com/shw9jkGNQir-YTVszGOd_NMdo_PP8XGWcruqIIeSZXU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9saXZl/LnN0YXRpY2ZsaWNr/ci5jb20vMjYwNy8z/Njg1MTQ5ODI4XzU0/MWYyNzAyYjAuanBn', 'https://imgs.search.brave.com/shw9jkGNQir-YTVszGOd_NMdo_PP8XGWcruqIIeSZXU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9saXZl/LnN0YXRpY2ZsaWNr/ci5jb20vMjYwNy8z/Njg1MTQ5ODI4XzU0/MWYyNzAyYjAuanBn','123'),
(6, 'M005', 'Universidad de Salamancaaaaaaaaaa', 'Salamancaaa', '1218', 'Universidad histórica', 'https://imgs.search.brave.com/rLnNM3QRmbY5RrJdmpkZRbO1-6u5Doo88tbGnKeZnk8/rs:fit:860:0:0:0/g:ce/aHR0cDovL3d3dy52/ZXJzYWxhbWFuY2Eu/Y29tL2ZvdG9zL3Vu/aXZlcnNpZGFkLmpw/Zw', 'https://imgs.search.brave.com/rLnNM3QRmbY5RrJdmpkZRbO1-6u5Doo88tbGnKeZnk8/rs:fit:860:0:0:0/g:ce/aHR0cDovL3d3dy52/ZXJzYWxhbWFuY2Eu/Y29tL2ZvdG9zL3Vu/aXZlcnNpZGFkLmpw/Zw', '123');

INSERT INTO `Usuario` (`id`, `dni`, `email`, `password`, `nombre`, `phone`, `token`) VALUES
(4, '1239899L', 'copado@gmail.com', 'b16b03c9dbda7e1bae9f057a8fbcbe482bda4d7ec9a6d31a4e8bb26b99537d79', 'copado', '21242323', ''),
(5, '242434343N', 'miguelon@gmail.com', 'e87687e2db8db437ae1eba7809ca17331b7b510977f8acfa0244fa485537c2a5', 'miguelon', '243434343', ''),
(6, '53434343', 'santi@gmail.com', '324ca5355e9d7d5f60fb23b379f5bad7d4a12013a8b89b46ec2392c3021d3a27', 'santi', '343543434', '');

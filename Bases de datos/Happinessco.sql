create database happinessco;
use happinessco;
 
create table usuarios (
    id int auto_increment primary key,
    nombre varchar(50),
    email varchar(100) unique,
    password varchar(100)
);
 
create table eventos (
    id int auto_increment primary key,
    fecha date,
    titulo varchar(100),
    ubicacion varchar(100),
    tipo varchar(50),
    descripcion text
);
 
create table galerias (
    id int auto_increment primary key,
    titulo varchar(100),
    id_evento int,
    foreign key (id_evento) references eventos(id)
);
 
create table imagenes (
    id int auto_increment primary key,
    titulo varchar(100),
    imagen varchar(255),
    id_galeria int,
    foreign key (id_galeria) references galerias(id)
);
 
create table favoritos (
    id_usuario int,
    id_evento int,
    primary key (id_usuario, id_evento),
    foreign key (id_usuario) references usuarios(id),
    foreign key (id_evento) references eventos(id)
);
 
insert into usuarios (nombre, email, password) values
('Carlos González', 'carlos@gmail.com', '1234'),
('Marco Álvarez', 'marco@gmail.com', '4321'),
('Luis Cristobal', 'luis@gmail.com', '0000');
 
insert into eventos (fecha, titulo, ubicacion, tipo, descripcion) values
('2026-06-05', 'Metropoli Gijón 2026', 'Gijón', 'musica', 'festival de musica'),
('2026-06-15', 'FETEN 2026: Magia en Escena', 'Gijón', 'Teatro', 'espectaculo de teatro'),
('2026-06-25', 'Llanes al Cubo 2026', 'Llanes', 'arte', 'exposiciones de arte'),
('2026-07-01', 'Premios Princesa de Asturias 2026', 'Oviedo', 'arte', 'ceremonia de entrega de premios'),
('2026-07-10', 'Semana Negra de Gijón 2026', 'Gijón', 'arte', 'festival literario'),
('2026-07-22', 'Pablo Alborán en Concierto', 'Oviedo', 'musica', 'concierto de musica'),
('2026-01-01', 'Festival de la Sidra Natural', 'Gijón', 'otros', 'evento de sidra'),
('2026-01-12', 'Picasso y los Maestros', 'Oviedo', 'arte', 'exposicion de arte'),
('2026-01-24', 'Descenso Internacional del Sella', 'Ribadesella', 'otros', 'evento deportivo de piraguas'),
('2026-02-12', 'Rally Princesa de Asturias', 'Oviedo', 'otros', 'evento deportivo de rally'),
('2026-02-09', 'Gijón Horse Show - Concurso de Saltos Internacional', 'Gijón', 'otros', 'evento deportivo de hípica'),
('2026-02-06', 'Estreno Nacional: "La Madre"', 'Avilés', 'teatro', 'estreno de obra de teatro');

insert into galerias (titulo, id_evento) values
('Galería Año Nuevo', 1),
('Galería Arte', 2),
('Galería Sella', 3);

insert into imagenes (titulo, imagen, id_galeria) values
('Foto 1 Ene', 'url1.jpg', 1), ('Foto 2 Ene', 'url2.jpg', 1), ('Foto 3 Ene', 'url3.jpg', 1),
('Obra 1', 'url4.jpg', 2), ('Obra 2', 'url5.jpg', 2), ('Obra 3', 'url6.jpg', 2),
('Piragua 1', 'url7.jpg', 3), ('Piragua 2', 'url8.jpg', 3), ('Piragua 3', 'url9.jpg', 3);

insert into favoritos (id_usuario, id_evento) values
(1, 1), (1, 2), (1, 4),
(2, 1), (2, 3), (2, 5),
(3, 2), (3, 3), (3, 6);

create view vista_galerias_historial as
select g.* from galerias g
join eventos e on g.id_evento = e.id
where e.fecha < '2026-02-28';

create view favoritos_usuario_1 as
select e.* from eventos e
join favoritos f on e.id = f.id_evento
where f.id_usuario = 1;

create view imagenes_evento_especifico as
select i.* from imagenes i
join galerias g on i.id_galeria = g.id
where g.id_evento = 2;

create view favoritos_proximos_usuario_2 as
select e.* from eventos e
join favoritos f on e.id = f.id_evento
where f.id_usuario = 2 and e.fecha > '2026-02-28';
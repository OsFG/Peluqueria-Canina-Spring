
create table duenios(
    id int not null auto_increment,
    nombre varchar(27) not null,
    apellido_p varchar(12) not null,
    apellido_m varchar(12) not null,
    telefono varchar(10) not null,
    correo varchar(50) not null unique,
    calle varchar(100) not null,
    numero_ext varchar(20) not null,
    colonia varchar(100) not null,
    cod_pos varchar(10),
    ciudad varchar(100) not null,
    activo tinyint not null default 0,

    primary key(id)
);


create table mascotas(
    id int not null auto_increment,
    nombre_masc varchar(18) not null,
    raza varchar(30) not null,
    color varchar(30) not null,
    alergico varchar(90) not null,
    observaciones varchar(120),
    atencion_especial varchar(90),
    activo tinyint not null,
    id_due int not null,

    primary key(id),
    constraint fk_mascotas_idDue foreign key(id_due) references duenios(id)
);


create table citas(
    id int not null auto_increment,
    cita_fyh datetime not null,
    cita_activa tinyint not null,
    id_mas int not null,

    primary key(id),
    constraint fk_citas_idMas foreign key(id_mas) references mascotas(id)
)
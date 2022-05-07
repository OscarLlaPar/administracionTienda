USE tienda_oscar_llamas_parra;

INSERT INTO categorias (nombre, descripcion)
VALUES ("Cuerda", "Instrumentos de cuerda frotada, punteada o percutida."),
("Viento metal","Instrumentos de viento metal: Tubas, trombones, trompetas,etc."),
("Viento madera","Instrumentos de viento madera: Flautas, clarinetes, saxofones, oboes, etc."),
("Percusion", "Instrumentos de percusion como cajas, tambores, platillos, etc."),
("Sintetizadores","Instrumentos electronicos MIDI.");

INSERT INTO roles (rol)
VALUES ("Cliente"),("Empleado"),("Administrador");

INSERT INTO usuarios (email, id_rol, clave, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni, fecha_baja)
VALUES ("admin@admin.com", 3, SHA2('admin',256), "Admin", "istra","dor", "C/ Administrador nº 1", "Zamora", "Zamora", "666666666", "12345678Z", null ),
("oscarllp1998@gmail.com", 3, SHA2('paso',256), "Óscar", "Llamas", "Parra", "C/ El Gran Puente nº 12", "Zamora", "Benavente", "656565656", "02659176P", null),
("empleado1@tienda.com", 2, SHA2('paso',256), "Manuel", "Perez","Perez", "C/ Tienda nº 1", "Zamora", "Zamora", "677777777", "96930871Q", null ),
("cliente1@mail.com", 1, SHA2('paso',256), "Pedro", "Fernandez","Fernandez", "C/ Ejemplo nº 1", "Zamora", "Zamora", "688888888", "17982331B",null );

INSERT INTO proveedores (nombre, direccion, localidad, provincia, telefono, cif, email, fecha_baja) 
VALUES ("Guitarras Paco","C/ Musica nº2","Zamora","Zamora","966666666","Q0541744I","paco@guitarras.com",null ),
("Vientos Eos","C/ Aire nº3","Benavente", "Zamora", "999666999","V09595059","vientos@musica.com",null),
("Gran Orquesta", "C/ Auditorio nº1", "Madrid", "Madrid", "919191919", "F13371836", "orquesta@orquesta.com",null),
("Mi Banda", "C/ Arbol nº12", "Valladolid", "Valladolid", "999111999", "V73423931", "mibanda@tiendabanda.com",null),
("Musica Paratodos", "C/ Parque nº5", "Zamora", "Zamora", "959959959", "G26137109", "paratodos@mimail.com",null),
("Clasicos Acustic", "C/ El Pino nº23", "Salamanca", "Salamanca", "987987987", "C19343599", "clasicos@instrumentos.com",null),
("Estudios Futura", "C/ Travesia nº1", "Madrid", "Madrid", "965969696", "S4348156C", "estudio@futura.com",null);

INSERT INTO productos (id_categoria,nombre,descripcion,precio,stock,fecha_baja,impuesto,imagen,audio,id_proveedor)
VALUES (1, "Guitarra", "Guitarra española tradicional. Con funda, pua y cuerdas incluidas.",71.90, 31, null, 12, "img/guitarra.jpg","audio/guitarra.wav",1),
(2, "Trompeta", "Trompeta normal. Para orquestas y bandas.",287.83, 13, null, 12, "img/trompeta.jpg","audio/trompeta.mp3",2),
(1, "Arpa", "Arpa grande profesional.", 982.36, 22, null, 12, "img/arpa.jpg",null,3),
(3, "Clarinete", "Clarinete profesional de orquesta.", 2543.12, 12, null, 12, "img/clarinete.jpg", null, 4),
(3, "Flauta dulce de madera", "Flauta dulce de madera para uso casual.", 7.9, 34, null, 12, "img/flautamadera.jpg", null, 4),
(3, "Flauta de pan", "Flauta de pan tradicional de America", 34.2, 23, null, 12, "img/flautapan.jpg", null, 4),
(1, "Guitarra electrica", "Guitarra electrica con estuche y correa. Amplificador no incluido.", 126.0, 13, null, 12, "img/guitarraelectrica.jpg", "audio/guitarraelectrica.wav", 1),
(4, "Pandereta de 7 sonajas", "Pandereta para celebraciones de 7 sonajas", 21.3, 22, null, 12, "img/pandereta.jpg", null, 5),
(1, "Piano de cola", "Piano de cola digital de 88 teclas", 10116.1, 3, null, 12, "img/pianocoladigital.jpg", "audio/piano.wav", 6),
(5, "Sintetizador Behringer", "Sintetizador digital de frecuencia modulada", 143.2, 12, null, 12, "img/sintetizadorbehringer.jpg", "audio/sintetizadorbehringer.wav", 7),
(5, "Sintetizador Take 5", "Sintetizador analógico con compresor", 216.5, 10, null,12, "img/sintetizadortake5.jpg", "audio/sintetizadortake5.mp3", 7),
(4, "Triangulo", "Triangulo musical con varilla", 35.0, 32, null,12, "img/triangulo.jpg", "audio/triangulo.wav", 5),
(2, "Trombon", "Trombon de orquesta con fuelle", 1560.2, 12, null, 12, "img/trombon.jpg", "audio/trombon.mp3",2),
(1, "Violin", "Violin de orquesta con estuche y varilla", 123.3, 11, null, 12, "img/violin.jpg", "audio/violin.wav", 6),
(4, "Xilofono", "Xilofono clasico con baquetas", 193.2, 10, null, 12, "img/xilofono.jpg", null, 5);

INSERT INTO metodos_pago (metodo_pago)
VALUES ("Tarjeta"),("Paypal");

INSERT INTO opciones_menu (id_rol,nombre_opcion,url_opcion)
VALUES (1,"Mis pedidos","ServletPedido"),
(2,"Gestionar productos","/productos"),
(2,"Gestionar clientes","/login/clientes"),
(2,"Gestionar pedidos","/pedidos"),
(2,"Gestionar proveedores","/proveedores"),
(3,"Gestionar productos","/productos"),
(3,"Gestionar clientes","/login/clientes"),
(3,"Gestionar pedidos","/pedidos"),
(3,"Gestionar proveedores","/proveedores"),
(3,"Gestionar empleados","/login/empleados"),
(3,"Configurar aplicacion","/config");

INSERT INTO configuracion (clave,valor,tipo)
VALUES ("nombreTienda","Tienda Orfeon","Texto"),
("direccionTienda", "C/Cosas nº 5", "Texto"),
("cifTienda", "D04272357", "Texto"),
("numFacturas","1","Numero"),
("adminLogado", "0", "Numero");
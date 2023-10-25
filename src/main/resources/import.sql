INSERT INTO proyecto_final.authorities (id) VALUES ('USER');
INSERT INTO proyecto_final.authorities (id) VALUES ('ADMIN');
INSERT INTO proyecto_final.authorities (id) VALUES ('PERSONAL');


INSERT INTO proyecto_final.person (birthdate, document_number, first_name, last_name, phone_number, status) VALUES ('2023-09-24 16:59:58', '239823 lp', 'asa', 'ddddd', '7143434', 1)
INSERT INTO proyecto_final.person (birthdate, document_number, first_name, last_name, phone_number, status) VALUES ('2023-10-10 19:34:57', '344343', 'jorge', 'alejo', '4564645', 2)
INSERT INTO proyecto_final.person (birthdate, document_number, first_name, last_name, phone_number, status) VALUES ('2023-10-10 19:35:27', '566556', 'erwin', 'alejo', '7845985', 3)
INSERT INTO proyecto_final.person (birthdate, document_number, first_name, last_name, phone_number, status) VALUES ('2023-10-10 19:35:49', '545455', 'majholy', 'alejo', '4545455', 1)

INSERT INTO proyecto_final.address (description) VALUES ('nuevo usuario de cliente')
INSERT INTO proyecto_final.address (description) VALUES ('calle 7')
INSERT INTO proyecto_final.address (description) VALUES ('ceja del Alto')
INSERT INTO proyecto_final.address (description) VALUES ('la paz')

INSERT INTO proyecto_final.user (created_at, mail, name_user, password, rol, updated_at, address_id, person_id) VALUES ('2023-09-05 22:03:40', 'jorge@gmail.com', 'jorgelag', '1234', '1', '2023-09-05 22:04:01', 1, 1)
INSERT INTO proyecto_final.user (created_at, mail, name_user, password, rol, updated_at, address_id, person_id) VALUES ('2023-10-10 19:38:40', 'jorge2@gmail.com', 'jorge123', '123', '2', '2023-10-10 19:39:09', 2, 2)
INSERT INTO proyecto_final.user (created_at, mail, name_user, password, rol, updated_at, address_id, person_id) VALUES ('2023-10-10 19:39:20', 'erwin@gmail.com', 'erwin222', '222', '3', '2023-10-10 19:39:53', 3, 3)
INSERT INTO proyecto_final.user (created_at, mail, name_user, password, rol, updated_at, address_id, person_id) VALUES ('2023-10-10 19:40:04', 'majholy@gmail.com', 'majholy555', '555', '1', '2023-10-10 19:40:28', 2, 4)


INSERT INTO proyecto_final.category (name_category) VALUES ('Perfiles de Aluminio')
INSERT INTO proyecto_final.category (name_category) VALUES ('Paneles de Aluminio Compuesto')
INSERT INTO proyecto_final.category (name_category) VALUES ('Cintas Doble Contacto')
INSERT INTO proyecto_final.category (name_category) VALUES ('Silicona')
INSERT INTO proyecto_final.category (name_category) VALUES ('Vidrios')
INSERT INTO proyecto_final.category (name_category) VALUES ('Acrilicos')


INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (0.5)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (0.6)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (0.7)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (0.8)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (0.9)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (1)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (1.2)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (1.3)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (1.4)
INSERT INTO proyecto_final.thickness (alum_thickness) VALUES (1.5)


INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 12')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 20')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 25')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 32')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 35')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 42')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('SERIE 4000')
INSERT INTO proyecto_final.aluminum_series (series_product) VALUES ('PERFILES VARIOS')


INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Blanco' ,'#FFFFFF')
INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Negro', '#000000')
INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Gris', '#808080')
INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Natural', '#ebebeb')
INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Champagne', '#e8d6b3')
INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Madera', '#685441')
INSERT INTO proyecto_final.alum_colors (color_name, hex) VALUES ('Dorado', '#efb810')


INSERT INTO proyecto_final.product (description_product, name_product, price, state, stock_total, category_id) VALUES ('Perfil de aluminio sirve para realizar ventanas o puertas de aluminio', 'Riel Superior', 150, 1, 180, 1)
INSERT INTO proyecto_final.product (description_product, name_product, price, state, stock_total, category_id) VALUES ('Sirve para ventanas y puertas', 'Riel Inferior', 180, 1, 260, 2)


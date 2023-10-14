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


INSERT INTO proyecto_final.alum_color (color_name, hex) VALUES ('Blanco' ,'#FFFFFF')
INSERT INTO proyecto_final.alum_color (color_name, hex) VALUES ('Negro', '#000000')
INSERT INTO proyecto_final.alum_color (color_name, hex) VALUES ('Gris', '#808080')


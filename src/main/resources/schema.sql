-- Corre después de que Hibernate crea o actualiza la tabla.
-- Asegura el "default 1" de estado también en tablas que ya existían.
ALTER TABLE denuncias ALTER COLUMN estado SET DEFAULT 1;

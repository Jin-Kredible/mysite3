CREATE SEQUENCE seq_gallery START WITH 1 INCREMENT BY 1 MAXVALUE 99999999;

DROP SEQUENCE seq_gallery;

SELECT * FROM gallery;

DELETE gallery;

COMMIT;

INSERT INTO gallery
     VALUES (seq_gallery.NEXTVAL,
             'dd',
             'dd',
             'dd');
create sequence seq_ingrediente;
create sequence seq_lanche;

-- Começa do 100 devido ao setup (data.sql)
alter sequence seq_ingrediente restart with 100;
alter sequence seq_lanche restart with 100;

CREATE TABLE ingrediente (
  id      BIGINT default seq_ingrediente.nextval primary key,
  nome    VARCHAR(100) NOT NULL,
  valor   NUMBER NOT NULL);

CREATE TABLE lanche (
  id         BIGINT default seq_lanche.nextval primary key,
  nome       VARCHAR(100) NOT NULL,
  url_imagem VARCHAR(1000));

CREATE TABLE ingredientes_do_lanche (
  id_lanche      BIGINT,
  id_ingrediente BIGINT);

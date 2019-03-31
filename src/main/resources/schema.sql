create sequence seq_ingrediente;
alter sequence seq_ingrediente restart with 100;
create sequence seq_lanche;
alter sequence seq_lanche restart with 100;

CREATE TABLE ingrediente (
  id      BIGINT default seq_ingrediente.nextval primary key,
  nome    VARCHAR(100) NOT NULL,
  valor   NUMBER NOT NULL);

CREATE TABLE lanche (
  id      BIGINT default seq_lanche.nextval primary key,
  nome    VARCHAR(100) NOT NULL);

CREATE TABLE ingredientes_do_lanche (
  id_lanche      BIGINT,
  id_ingrediente BIGINT);

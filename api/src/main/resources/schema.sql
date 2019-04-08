create sequence seq_ingrediente;
create sequence seq_lanche;
create sequence seq_promocao;
create sequence seq_promocao_regras;

-- Começa do 100 devido ao setup (data.sql)
alter sequence seq_ingrediente restart with 100;
alter sequence seq_lanche restart with 100;
alter sequence seq_promocao restart with 100;
--alter sequence seq_promocao_regras restart with 100;

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

CREATE TABLE promocao (
  id                    BIGINT default seq_promocao.nextval primary key,
  nome                  VARCHAR(100) NOT NULL,
  tipo_desconto         VARCHAR(100) NOT NULL,
  porcentagem_desconto  NUMBER(3,1)          ,
  ordem                 BIGINT       NOT NULL);

CREATE TABLE promocao_regras (
  id             BIGINT default seq_promocao_regras.nextval primary key,
  id_promocao    BIGINT      NOT NULL,
  id_ingrediente BIGINT      NOT NULL,
  operador       VARCHAR(10) NOT NULL,
  quantidade     BIGINT      NOT NULL,
  desconto       BIGINT);

------------------
-- Setup dos dados
------------------

-- Ingredientes
insert into ingrediente (id, nome, valor) values (1, 'Alface', 0.40);
insert into ingrediente (id, nome, valor) values (2, 'Bacon', 2.00);
insert into ingrediente (id, nome, valor) values (3, 'Hamburger de carne', 3.00);
insert into ingrediente (id, nome, valor) values (4, 'Ovo', 0.80);
insert into ingrediente (id, nome, valor) values (5, 'Queijo', 1.50);
commit;

-- Lanches
insert into lanche (id, nome, url_imagem) values (1, 'X-Bacon','https://aimg.comendo.com.br/produtos/550/161130192208583f430081cf5.jpg');
insert into lanche (id, nome, url_imagem) values (2, 'X-Burguer','https://aimg.comendo.com.br/produtos/550/161130192230583f431602c61.jpg');
insert into lanche (id, nome, url_imagem) values (3, 'X-Egg','https://aimg.comendo.com.br/produtos/550/161130192528583f43c8be9d4.jpg');
insert into lanche (id, nome, url_imagem) values (4, 'X-Egg Bacon','https://aimg.comendo.com.br/produtos/550/161130192741583f444da697b.jpg');
commit;

-- Ingredientes de cada lanche padrão

--X-Bacon: Bacon, hambúrguer de carne e queijo
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (1, 2);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (1, 3);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (1, 5);

--X-Burger: Hambúrguer de carne e queijo
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (2, 3);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (2, 5);

--X-Egg: Ovo, hambúrguer de carne e queijo
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (3, 4);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (3, 3);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (3, 5);

--X-Egg Bacon:  Ovo, bacon, hambúrguer de carne e queijo
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (4, 4);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (4, 2);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (4, 3);
insert into ingredientes_do_lanche (id_lanche, id_ingrediente) values (4, 5);
commit;

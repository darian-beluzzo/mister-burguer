-- Setup de dados
insert into ingrediente (id, nome, valor) values (1, 'Alface', 0.40);
insert into ingrediente (id, nome, valor) values (2, 'Bacon', 2.00);
insert into ingrediente (id, nome, valor) values (3, 'Hambúrguer de carne', 3.00);
insert into ingrediente (id, nome, valor) values (4, 'Ovo', 0.80);
insert into ingrediente (id, nome, valor) values (5, 'Queijo', 1.50);
commit;

insert into lanche (id, nome) values (1, 'X-Bacon');
insert into lanche (id, nome) values (2, 'X-Burguer');
insert into lanche (id, nome) values (3, 'X-Egg');
insert into lanche (id, nome) values (4, 'X-Egg Bacon');
commit;

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

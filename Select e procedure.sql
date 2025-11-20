use systenez;

-- Select de todos os dados da tabela usuário.
select * from usuario;

-- Select de todos os dados da tabela produto.
select * from produto;

-- Select de todos os dados da tabela venda.
select * from venda;



-- Criação de procedure para relatório de estoque.

delimiter //
create procedure relatorioEstoque()
begin
    select id, nome, quantidade
    from produto;
end //
delimiter ;

call relatorioEstoque;

-- Criação de procedure para relatório de vendas.

delimiter //
create procedure relatorioVendas()
begin
    select v.id, p.nome, v.quantidade_vendida, v.data_venda, (v.quantidade_vendida * p.preco_venda) as total
    from venda v
    join produto p ON v.produto_id = p.id;
end //
delimiter ;

call relatorioVendas;



-- Criação de procedure para relatório com margem de lucro.

delimiter //
create procedure relatorioMargemDeLucro()
begin
    select id, nome, preco_custo, preco_venda, (preco_venda - preco_custo) as margem_de_lucro
    from produto;
end //
delimiter ;

call relatorioMargemDeLucro;


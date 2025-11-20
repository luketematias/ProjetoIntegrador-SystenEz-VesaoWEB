use systenez;

insert into usuario (nome, senha, tipo) values
('Lucas', '123', 'Administrador'),
('Lukete', '456', 'Gerente de Estoque'),
('Lusquinha', '789', 'Vendedor');



insert into produto (nome, preco_custo, preco_venda, categoria, quantidade) values
('Produto A', 10.00, 15.00, 'Eletrônicos', 50),
('Produto B', 20.00, 30.00, 'Eletrônicos', 30),
('Produto C', 5.00, 8.00, 'Acessórios', 100),
('Produto D', 12.00, 18.00, 'Acessórios', 25),
('Produto E', 50.00, 70.00, 'Eletrodomésticos', 10);


insert into venda (produto_id, quantidade_vendida, data_venda) values
(1, 5, '2024-10-01'),
(2, 3, '2024-10-02'),
(3, 10, '2024-10-03'),
(4, 2, '2024-10-04'),
(5, 1, '2024-10-05');




INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito'), (2, 'Cartão de Débito'), (3, 'Dinheiro')

INSERT INTO empresa (id, ativa, nome) VALUES (1, true, 'Casas Bahia'), (2, false, 'Ponto Frio')

INSERT INTO cliente (id, nome, email, telefone, empresa_id) VALUES (1, 'André', 'andre_s.p94@hotmail.com', '81996476138', 1), (2, 'João', 'joao@hotmail.com', '81991234566', 1), (3, 'José', 'jose@hotmail.com', '81999876543', 2)

INSERT INTO tipo_doce (id, descricao, qtd_estoque, valor_unitario) VALUES (1, 'Brigadeiro', 2, 5.49), (2, 'Bem casado', 1, 2.69)

--insert into venda (cliente_id, data_compra, data_pagamento, forma_pagamento_id, pago, valor_pago, valor_total) values (1, null, null , 1, true, 5.49, 5.49)
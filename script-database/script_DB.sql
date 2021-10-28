create schema public;

create table cliente (
	id bigserial not null, 
	email varchar(255), 
	nome varchar(255) unique, 
	telefone varchar(255), 
	empresa_id int8 not null, 
	primary key (id)
);

create table empresa (
	id bigserial not null, 
	ativa boolean not null, 
	nome varchar(255) unique, 
	primary key (id)
);

create table forma_pagamento (
	id bigserial not null, 
	descricao varchar(255) unique, 
	primary key (id)
);

create table itens_venda (
	id bigserial not null, 
	quantidade int4 not null, 
	tipo_de_doce_id int8 not null, 
	venda_id int8, 
	primary key (id)
);

create table tipo_doce (
	id bigserial not null, 
	descricao varchar(255) unique, 
	qtd_estoque int4, 
	valor_unitario numeric(19, 2), 
	primary key (id)
);

create table venda (
	id bigserial not null, 
	data_compra timestamp, 
	data_pagamento timestamp, 
	pago boolean, 
	valor_pago numeric(19, 2), 
	valor_total numeric(19, 2), 
	cliente_id int8, 
	forma_pagamento_id int8, 
	primary key (id)
);

alter table cliente add constraint fk_empresa_id foreign key (empresa_id) references empresa;
alter table itens_venda add constraint fk_tipo_doce foreign key (tipo_de_doce_id) references tipo_doce;
alter table itens_venda add constraint fk_venda_id foreign key (venda_id) references venda;
alter table venda add constraint fk_cliente_id foreign key (cliente_id) references cliente;
alter table venda add constraint fk_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento;

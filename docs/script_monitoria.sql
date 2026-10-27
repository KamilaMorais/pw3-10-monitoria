Create database DadosMonitoria

Use DadosMonitoria 

Create table VagaMonitoria 
(
id bigint primary key,
disciplina varchar(50),
nomeMonitor varchar (100),
vagasTotais int,
vagasOcupadas int,
aberta bit default 1 --1 ativo e 0 inativo
);
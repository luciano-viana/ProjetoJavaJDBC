package sql;

public class ComandosSQLPostgre {
	
	//alter table userposjava"tabela" add unique (id);
	/*Utilizado para transformar o id da tabela userposjava para ser uma chave única, ter restrição de único
	 *Cria um constraints na tabela
	 *constraints: Está restrição cria um índice único para um conjunto de colunas ou uma coluna para Chave Primaria.
	 * */
	
	//Criar tablea
	/*
		create table telefoneuser (
		id bigint not null,
		numero character varying (255) not null,
		tipo character varying (255) not null,
		usuariopessoa bigint not null,
		constraint telefone_id primary key (id));
	 */
	
	//Transformando usuariopessoa em uma foreing key para apontar para a tabela userposjava
	/*alter table telefoneuser add foreign key (usuariopessoa) references userposjava(id)*/
	 
	//Criar sequência de id automático
	/*
		CREATE SEQUENCE user_telefone_seq
		  INCREMENT 1
		  MINVALUE 1
		  MAXVALUE 9223372036854775807
		  START 1
		  CACHE 1;
		ALTER TABLE user_telefone_seq
		  OWNER TO postgres;
    */
	
	//Adicionar a sequência
	/*ALTER TABLE telefoneuser ALTER COLUMN id SET DEFAULT nextval('user_telefone_seq'::regclass);*/
	
	//Selecionar dados entre 2 tabelas "junção de dados entre 2 tabelas"
	/*
		select * from telefoneuser tl
		INNER JOIN userposjava uj
		ON tl.usuariopessoa = uj.id
		where uj.id = 11
	*/
}

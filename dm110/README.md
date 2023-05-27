## Projeto desenvolvido na disciplina DM110 - Desenvolvimento Java EE

Neste projeto foi utilizado os conceitos aprendidos em aula sobre:

- Java EE
- Enterprise JavaBeans
- Java Persistence API
- Processamento de mensagens MDB.

O projeto possui duas especificações:

**1.** Serviços REST, Session Bean Stateless e Entities que suportam as seguintes operações:
- inclusão de um registro
- busca de um registro através do seu identificador (CNPJ)
- listagem de registros
- atualização de um registro

>Os serviços REST chamam o Session Bean para acessar o banco de dados.
O Session Bean chama o serviço de mensagem para ele efetuar o serviço de auditoria.

**2.** Serviço de mensagem (MDB) que efetua um registro de auditoria.
>O MDB chama um Session Bean para acessar o banco de dados.

Instruções para utilização do Rest API:

POST
http://127.0.0.1:8080/dm110-web/api/suppliers/create

body:

{
    "cnpj":103000212, 
    "name":"Microsoft", 
    "email":"sales@microsoft.com", 
    "cep":37540000,
    "date":"22/05/2023", 
    "rate":8
}

GET
http://127.0.0.1:8080/dm110-web/api/suppliers/find
GET
http://127.0.0.1:8080/dm110-web/api/suppliers/find/1

PUT
http://127.0.0.1:8080/dm110-web/api/suppliers/update/1

body:

{
    "cnpj":103000212, 
    "name":"Microsoft", 
    "email":"marketing@microsoft.com", 
    "cep":37540000,
    "date":"29/05/2023", 
    "rate":8
}

Comandos utilizados para criar o banco de dados:

CREATE TABLE SUPPLIER (
           CNPJ INTEGER NOT NULL,
           NOME VARCHAR(20) NOT NULL,
           EMAIL VARCHAR(30) NOT NULL,
           CEP INTEGER NOT NULL,
           DATE VARCHAR(20) NOT NULL,
           RATE INTEGER NOT NULL,
           PRIMARY KEY (CNPJ)
);

 CREATE TABLE AUDITTABLE (
           ID INTEGER IDENTITY PRIMARY KEY,
           CNPJ INTEGER NOT NULL,
           OPERATION VARCHAR(30) NOT NULL,
           LASTMODIFIED VARCHAR(30)
);

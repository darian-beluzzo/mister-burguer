## Backend

Para construção foi utilizado o framework Spring Boot que é responsável por expor as API's REST com a finalidade de atender ao frontend, neste caso.

Para gerenciamento das dependências foi utilizado o Maven.

A IDE utilizada foi o Intellij IDEA

Para rodar em dev basta acessar o diretório **api** e rodar o comando:
```
mvn spring-boot:run
```

O backend irá subir no endereço http://localhost:8081

## Arquitetura

### Domain-Driven Design

Esta é a implementação simples utilizando a abordagem de modelagem DDD onde o design é guiado pelo domínio, ou seja, o objetivo é resolver problemas na complexidade do negócio.

### Padrão Domain Model

### Padrão Data Transfer Object (DTO)
Foi utilizado o padrão  para mapear e converter os objetos recebidos da camada de apresentação (React) do frontend e convertê-los para as entities e vice-versa. Esta conversão é feita nos controllers Rest.

Nesta abordagem a principal vantagem é que definimos exatamente quais atributos e quais tipos serão aceitos no request e quais serão enviados no response.
A principal desvantagem no meu ponto de vista é que pode gerar um grande boilerplate de classes.



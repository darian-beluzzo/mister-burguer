## Backend

Foi utilizado o framework Spring Boot que é responsável por expor as API's REST com a finalidade de atender, neste caso, ao frontend.

Suas principais vantagens são a leveza, fácil configuração, customização e empacotamento. 

Para gerenciamento das dependências foi utilizado o Maven.

E a IDE utilizada foi o Intellij IDEA.

Para rodar em modo dev basta acessar o diretório **api** e rodar o comando:
```
mvn spring-boot:run
```

O backend irá subir no endereço http://localhost:8081

## Arquitetura

### Domain-Driven Design

Esta é a implementação simples utilizando a abordagem de modelagem DDD onde o design é guiado pelo domínio, ou seja, o objetivo é resolver problemas na complexidade do negócio.

### Padrão Domain Model

Este padrão permite criar uma estrutura de objetos que utiliza plenamente todas as forças da orientação a objetos, tornando a arquitetura da aplicação mais flexível, fácil de manter e evoluir com o passar do tempo.

O principal motivo desta escolha é o conhecimento que tenho sobre este pattern.

### Padrão Data Transfer Object (DTO)
Este padrão foi utilizado para mapear e converter os objetos recebidos da camada de apresentação e convertê-los para as entities e vice-versa. Esta conversão é feita nos controllers Rest.

Nesta abordagem a principal vantagem é que definimos exatamente quais atributos e quais tipos serão aceitos no request e quais serão enviados no response, evitando assim expor informações sensíveis ou trafegar mais do que o necessário.
A principal desvantagem no meu ponto de vista é que pode gerar um grande boilerplate de classes.



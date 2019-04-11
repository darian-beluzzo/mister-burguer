# mister-burger

## Requisitos

node v11.6.0

yarn 1.15.2

docker version 18.09.4, build d14af54

docker-compose version 1.23.2, build 1110ad01

Apache Maven 3.6.0

java version "1.8.0_151"

## Backend (diretório api)

Spring-Boot + Maven
IDE Intellij IDEA

Para rodar em dev basta acessar o diretório **api** e rodar o comando:
```
mvn spring-boot:run
```
O backend irá subir no endereço http://localhost:8081

## Frontend (diretório frontend)

React + yarn
IDE VS Code

para rodar em dev basta acessar o diretório **frontend** e rodar os comandos:
```
yarn
yarn start
```
O frontend irá subir no endereço http://localhost:3000

## Frontend e Backend em modo produção

Para realizar o build dos artefatos e subir a aplicação utilizando os contêineres docker basta rodar os comandos abaixo:
```
cd docker/prod
./build-run.sh
```

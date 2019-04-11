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

Para rodar em dev basta acessar o diretório **frontend** e rodar os comandos:
```
yarn
yarn start
```
O frontend irá subir no endereço http://localhost:3000

## Frontend e Backend em modo produção

### Build automático
Para realizar o build dos artefatos e subir a aplicação utilizando os contêineres docker basta rodar os comandos abaixo:
```
cd docker/prod
./build-run.sh
```

A aplicação estará acessível no endereço http://localhost

### Build manual

**Build do backend**
```
cd docker/prod
export MAVEN_CONFIG=~/.m2
docker run -u $UID --rm -v $PWD/../../api:/app -v $MAVEN_CONFIG:/app/.m2 -e MAVEN_CONFIG=/app/.m2 -w /app --name api-build maven:3.6-jdk-8 mvn -Duser.home=/app package
```

**Build do frontend**
```
docker run -u $UID --rm -v $PWD/../../frontend:/app -w /app --name react-build node /bin/bash -c "yarn; yarn build";
```
**Inicia os servicos**
```
docker-compose up -d
```

A aplicação estará acessível no endereço http://localhost

# mister-burger

## Requisitos

node v11.6.0

yarn 1.15.2

docker version 18.09.4, build d14af54

docker-compose version 1.23.2, build 1110ad01

Apache Maven 3.6.0

java version "1.8.0_151"

## [Backend](/api/#readme)

## [Frontend](/frontend/#readme)

## [Build](/docker/prod/#readme)

## [jenkins](/docker/jenkins/#readme)

## Backend (diretório api)

Spring-Boot + Maven

IDE Intellij IDEA

Para rodar em dev basta acessar o diretório **api** e rodar o comando:
```bash
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
export MAVEN_CONFIG=~/.m2 ## alterar caso o seu repositório esteja em um local diferente do padrão
./build-run.sh
```

A aplicação estará acessível no endereço http://localhost

### Build manual

**Build do backend**

*Partindo da raíz do projeto.*

```
cd docker/prod
export MAVEN_CONFIG=~/.m2 ## alterar caso o seu repositório esteja em um local diferente do padrão
docker run -u $UID --rm -v $PWD/../../api:/app -v $MAVEN_CONFIG:/app/.m2 -e MAVEN_CONFIG=/app/.m2 -w /app --name api-build maven:3.6-jdk-8 mvn -Duser.home=/app package
```

**Build do frontend**

*Assumindo que ainda esteja no diretório docker/prod*

```
docker run -u $UID --rm -v $PWD/../../frontend:/app -w /app --name react-build node /bin/bash -c "yarn; yarn build";
```

**Inicia os servicos**
```
docker-compose up -d
```

A aplicação estará acessível no endereço http://localhost

## Integração Contínua (Jenkins)

Após subir o container do Jenkins, os testes serão executados sempre que houver commit em até 5 minutos (tempo de consulta ao SCM configurado no Jenkins).

Para fazer o build da imagem do jenkins com o maven rodar os comandos:

*Partindo da raíz do projeto.*

```
cd docker/jenkins
docker build -t jenkins_maven .
```

Para inicializar o jenkins é necessário rodar os comandos:

*Assumindo que ainda esteja no diretório docker/jenkins*

```
docker run -u root --rm -d -p 8082:8080 -v $PWD/jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --name jenkins jenkins_maven
```

O jenkins estará acessível no endereço http://localhost:8082

Usuário e senha foram enviados por email.

## Build

### Build automático

Para realizar o build dos artefatos e subir a aplicação utilizando os contêineres docker basta rodar os comandos abaixo:

*Partindo da raíz do projeto.*

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

**Para iniciat os serviços rodar o comando:**
```
docker-compose up -d
```

A aplicação estará acessível no endereço http://localhost


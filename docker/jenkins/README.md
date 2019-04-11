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

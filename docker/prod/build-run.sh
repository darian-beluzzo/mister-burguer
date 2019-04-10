#!/bin/bash

FILE_PATH=$(test -L $0 && ls -l $0 | awk '{print $11;}' || echo "$0")
REAL_PATH=`readlink -f $(dirname $FILE_PATH)` #Diretório onde está este script (full path)

cd $REALPATH

# Build do backend
docker run -u $UID --rm -v $REAL_PATH/../../api:/app -v $HOME/.m2:/app/.m2 -e MAVEN_CONFIG=/app/.m2 -w /app --name api-build maven:3.6-jdk-8 mvn -Duser.home=/app package

# Build do frontend
docker run -u $UID --rm -v $REAL_PATH/../../frontend:/app -w /app --name react-build node /bin/bash -c "yarn; yarn build";

docker-compose up -d

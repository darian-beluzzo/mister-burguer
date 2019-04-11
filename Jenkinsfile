

pipeline {
    script {
        env.MAVEN_CONFIG = readFile 'maven.env'
        env.MAVEN_CONFIG = ${env.MAVEN_CONFIG} - 'MAVEN_CONFIG='
    }

    agent {
        docker {
            image 'maven:3.6-jdk-8' 
            args "-v ${env.MAVEN_CONFIG}:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                dir('api')
                sh 'mvn test' 
            }
        }
    }
}

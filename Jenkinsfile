

pipeline {
    agent {
        docker {
            image 'maven:3.6-jdk-8' 
            args "-v ${props.MAVEN_CONFIG}:/root/.m2' 
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

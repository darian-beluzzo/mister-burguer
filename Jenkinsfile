pipeline {
    agent {
        docker {
            image 'maven:3.6-jdk-8' 
            args '-v ~/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -v' 
            }
        }
    }
}


pipeline {
    agent {
        docker {
            image 'maven:3.6-jdk-8' 
            args '-v /home/dab/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                echo "${env.PATH}"
                sh 'mvn -v' 
            }
        }
    }
}

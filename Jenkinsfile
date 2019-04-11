

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
                script {
                    env.MAVEN_REPO = readFile '.env'
                }
                echo "${env.MAVEN_REPO}"
                sh 'mvn test' 
            }
        }
    }
}

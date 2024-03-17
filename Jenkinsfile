pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'build'
                chmod +x gradlew
                ./gradlew clean build
            }
        }
        stage('test') {
            steps {
                echo 'testing the application...'
            }
        }
        stage('deploy') {
            steps {
                echo 'deploying the application...'
            }
        }
    }
}
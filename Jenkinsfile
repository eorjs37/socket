pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh '''
                    echo 'build'
                    ./gradlew clean bootJar
                '''
            }
        }
        stage('ssh server'){
            steps{
                sshagent(credentials: ['aws-server']){
                    sh """
                        ssh -o StrictHostKeyChecking=no ubuntu@15.165.120.8 '
                        ls -al
                        cat linux.txt
                        '
                    """
                }
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
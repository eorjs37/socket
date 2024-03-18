pipeline {
    environment{
        repository ="eorjs37/socket"
        DOCKERHUB_CREDENTIALS = credentials('jenkins')
        dockerImage = ''
        BUILD_NUMBER ='1.0'
    }
    agent any
    stages {
        stage('build1') {
            steps {
                sh '''
                    echo 'build'
                    ./gradlew clean bootJar
                '''
            }
        }
        stage('Building our image'){
            steps{
                script{
                    dockerImage = docker.build repository + ":$BUILD_NUMBER"
                }
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
    }
}
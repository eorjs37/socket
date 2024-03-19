pipeline {
    environment{
        repository ="eorjs37/socket"
        DOCKERHUB_CREDENTIALS = credentials('jenkins')
        dockerImage = ''
        BUILD_NUMBER ='0.1'
    }
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
        stage('Building our image'){
            steps{
                script{
                    dockerImage = docker.build repository + ":$BUILD_NUMBER"
                }
            }
        }
        stage('push our image'){
            steps{
                echo "Push Docker"
                script{
                    docker.withRegistry('https://registry.hub.docker.com',DOCKERHUB_CREDENTIALS){
                        dockerImage.push("1.0")
                    }
                }
            }
        }
        stage('ssh server'){
            steps{
                sshagent(credentials: ['aws-server']){
                    sh """
                        ssh -o StrictHostKeyChecking=no ubuntu@15.165.120.8 '
                        ls -al
                        '
                    """
                }
            }
        }
    }
}
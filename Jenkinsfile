pipeline {
    agent any
    environment {
        DOCKER_HUB_TOKEN = credentials('dockerhub-token')
    }
    stages {
        stage("Git checkout") {
            steps {
                git credentialsId: 'github-cred', url: 'https://github.com/Emii-lia/demoic.git'
            }
        }
        stage("Build") {
            steps {
                sh "mvn clean install"
            }
        }
        stage("Unit Test") {
            steps {
                sh "mvn test"
            }
        }
        stage("Build docker image") {
            steps {
                sh "docker build -t emiilia/demoic:0.1.0 ."
                sh '''
                    echo "$DOCKER_HUB_TOKEN" | docker login -u emiilia --password-stdin
                '''
                sh "docker push emiilia/demoic:0.1.0"
            }
        }
    }
}

pipeline {
    agent any

    tools {
        maven 'Maven3'  // تأكد انك عامل Tool باسم Maven3 في Jenkins
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        echo "🔨 Building on Unix..."
                        sh 'mvn clean compile'
                    } else {
                        echo "🔨 Building on Windows..."
                        bat 'mvn.cmd clean compile'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        echo "🧪 Testing on Unix..."
                        sh 'mvn test'
                    } else {
                        echo "🧪 Testing on Windows..."
                        bat 'mvn.cmd test'
                    }
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    if (isUnix()) {
                        echo "📦 Packaging on Unix..."
                        sh 'mvn package'
                    } else {
                        echo "📦 Packaging on Windows..."
                        bat 'mvn.cmd package'
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check the logs!"
        }
    }
}
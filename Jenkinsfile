pipeline {
    agent any

    tools {
        maven 'Maven3'
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
                    def mvnCmd = isUnix() ? 'mvn' : 'mvn.cmd'
                    echo "🔨 Building the project..."
                    sh("${mvnCmd} clean compile")   // Unix
                    // bat("${mvnCmd} clean compile") // Windows, uncomment if on Windows
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def mvnCmd = isUnix() ? 'mvn' : 'mvn.cmd'
                    echo "🧪 Running tests..."
                    sh("${mvnCmd} test")   // Unix
                    // bat("${mvnCmd} test") // Windows
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
                    def mvnCmd = isUnix() ? 'mvn' : 'mvn.cmd'
                    echo "📦 Packaging project..."
                    sh("${mvnCmd} package")   // Unix
                    // bat("${mvnCmd} package") // Windows
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
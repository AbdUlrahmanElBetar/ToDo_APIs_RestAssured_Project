pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        MVN_CMD = isUnix() ? 'mvn' : 'mvn.cmd'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "🔨 Building the project..."
                bat("${env.MVN_CMD} clean compile") // Windows
                // sh("${env.MVN_CMD} clean compile") for Unix, handled by MVN_CMD
            }
        }

        stage('Test') {
            steps {
                echo "🧪 Running tests..."
                bat("${env.MVN_CMD} test")
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "📦 Packaging project..."
                bat("${env.MVN_CMD} package")
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
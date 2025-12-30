pipeline {
    agent any

    tools {
        // Make sure this name matches Maven name in Jenkins → Global Tool Configuration
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/shabina29/bdd-web-automation.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                echo 'Running Maven tests'
                bat 'mvn clean test'
            }
        }
    }

post {
    always {
        cucumber(
            buildStatus: 'UNSTABLE',
            fileIncludePattern: '**/cucumber.json'
        )
    }
}

}

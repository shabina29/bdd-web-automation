pipeline {
    agent any

    tools {
        jdk 'JDK_17'
        maven 'Maven_3'
    }

    parameters {
        choice(
            name: 'ENV',
            choices: ['QA', 'STAGE', 'PROD'],
            description: 'Select environment'
        )

        choice(
            name: 'TEST_TYPE',
            choices: ['SMOKE', 'REGRESSION'],
            description: 'Select test type'
        )
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
                echo "Environment: ${params.ENV}"
                echo "Test Type: ${params.TEST_TYPE}"

                script {
                    if (params.TEST_TYPE == 'SMOKE') {
                        bat "mvn clean test -Dcucumber.filter.tags=@Smoke -Denv=${params.ENV}"
                    } else {
                        bat "mvn clean test -Dcucumber.filter.tags=@Regression -Denv=${params.ENV}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed'
        }
        success {
            echo 'Automation executed successfully'
        }
        failure {
            echo 'Automation execution failed'
        }
    }
}

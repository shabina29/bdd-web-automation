pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    // ✅ Nightly regression trigger (1 AM daily)
    triggers {
        cron('H 1 * * *')
    }

    // ✅ Parameters (used in email + future control)
    parameters {
        choice(
            name: 'ENV',
            choices: ['QA', 'STAGE', 'PROD'],
            description: 'Select environment'
        )
        choice(
            name: 'TEST_TYPE',
            choices: ['REGRESSION'],
            description: 'Nightly regression execution'
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
                echo 'Running Maven tests'
                bat 'mvn clean test'
            }
        }
    }

    // ✅ Email notifications
    post {

        success {
            emailext(
                subject: "✅ Jenkins SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build SUCCESS

Job Name: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Environment: ${params.ENV}
Test Type: ${params.TEST_TYPE}

Build URL:
${env.BUILD_URL}
""",
                to: "qa-team@company.com"
            )
        }

        unstable {
            emailext(
                subject: "⚠ Jenkins UNSTABLE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build UNSTABLE (Test Failures)

Job Name: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Environment: ${params.ENV}
Test Type: ${params.TEST_TYPE}

Check details:
${env.BUILD_URL}
""",
                to: "qa-team@company.com"
            )
        }

        failure {
            emailext(
                subject: "❌ Jenkins FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build FAILED

Job Name: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}

Immediate attention required:
${env.BUILD_URL}
""",
                to: "qa-team@company.com"
            )
        }
    }
}

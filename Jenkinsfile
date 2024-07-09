pipeline {
    agent any

    tools {
        // Instalar Maven desde las herramientas globales de Jenkins
        maven 'Maven 3.6.3'
        jdk 'jdk-11'
        jfrog 'jfrog-cli'
    }

    environment {
        ARTIFACTORY_URL = 'http://jfrog_artifactory_eva5:8082/artifactory'
        ARTIFACTORY_REPO = 'eva5b-myconstruction'
        ARTIFACTORY_CREDS = credentials('jfrog-creds') // ID de las credenciales de Artifactory en Jenkins
    }

    stages {

        stage('Checkout') {
            steps {
                // Clonar el código fuente desde el repositorio
                git url: 'https://github.com/Wildesdar37/eva_5b_mycontruction.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Cambiar al directorio myconstruction antes de ejecutar comandos
                dir('myconstruction') {
                    // Limpiar y construir el proyecto Maven
                    sh 'mvn clean package -DskipTests'
                }
            }
        }


        stage('Upload to jfrog') {
            steps {
                dir('myconstruction') {
                    // Show the installed version of JFrog CLI.
                    jf '-v'

                    // Show the configured JFrog Platform instances.
                    jf 'c show'

                    // Ping Artifactory.
                    jf 'rt ping'

                    // Create a file and upload it to a repository  in Artifactory
                    jf "rt u target/myconstruction-0.0.1-SNAPSHOT.war eva5b-myconstruction/${BUILD_TAG}/"

                    // Publish the build-info to Artifactory.
                    jf 'rt bp'
                }
            }
        }
    }

    post {
        success {
            echo 'Build and tests were successful!'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}

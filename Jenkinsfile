pipeline {
    agent any
    environment {
        DOCKER_PASSWORD = credentials("parolaDocker")
        GITHUB_TOKEN = credentials("2f91c034-8fc8-4ef5-85a1-a3e1d5f6936c")        
    }

    stages {
        stage('Build & Test') {
            steps {
                sh './gradlew clean build'
            	}
            }
	stage('Tag image')     {
	     steps {
		script {
		   sh([script: 'git fetch --tag', returnStdout: true]).trim()
		   env.MAJOR_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 1', returnStdout: true]).trim()
		   env.MINOR_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 2', returnStdout: true]).trim()
		   env.PATCH_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 3', returnStdout: true]).trim()
		   env.IMAGE_TAG = "${env.MAJOR_VERSION}.\$((${env.MINOR_VERSION} + 1)).${env.PATCH_VERSION}"
		}
		sh "docker build -t sebastica/hello-img:${MAJOR_VERSION}.\$((${MINOR_VERSION} + 1)).${PATCH_VERSION} ."		
		sh "git tag ${env.IMAGE_TAG}"		
		sh "git push https://$GITHUB_TOKEN@github.com/Marvellops/service.git ${env.IMAGE_TAG}"
	      }
	   }
	stage('Docker start') {
            steps {
                sh "IMAGE_TAG=${env.IMAGE_TAG} docker-compose up -d hello"
            }
        }
        stage('Test E2E') {
            steps {
                sh './gradlew testE2E'
            }
        }                
    }    
}

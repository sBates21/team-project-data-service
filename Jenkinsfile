node {
	stage('Checkout') {
		git url: 'https://github.com/sBates21/team-project-data-service.git'
	}
	stage('Gradle build') {
		bat 'gradle build'
	}
}
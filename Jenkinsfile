#!/usr/bin/env groovy

node {
    try {
        stage('Checkout') {
            git url: 'https://github.com/devhvm/snv_donvibaocao.git', branch: 'development'
        }
        stage("build") {
            docker.image("jhipster/jhipster:v5.8.2").inside("-v /home/vunt/maven/.m2:/root/.m2") { c ->
                stage('check java') {
                    sh "java -version"
                }

                stage('clean') {
                    sh "chmod +x mvnw"
                    sh "./mvnw clean"
                }

                // stage('backend tests') {
                //     try {
                //         sh "./mvnw test"
                //     } catch(err) {
                //         throw err
                //     } finally {
                //         junit '**/target/surefire-reports/TEST-*.xml'
                //     }
                // }

                stage('packaging') {
                    sh "./mvnw verify -Pprod -DskipTests"
                    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
                }
            }
        }

        def dockerImage
        stage('build docker') {
            sh "cp -R src/main/docker target/"
            sh "cp target/*.war target/docker/"
            echo 'I only execute on the development branch'
            dockerImage = docker.build('snv-development/service-donvibaocao', 'target/docker')
        }
        stage ('Run') {

            sh "cp -R src/main/docker target/"
            sh "cp target/*.war target/docker/"
            //sh "docker system prune -a -f --volumes"

            echo 'I only execute on the development branch'
            try {
                sh '( docker stop donvibaocao-app-dev > /dev/null && echo Stopped container donvibaocao-app-dev && \
      docker rm donvibaocao-app-dev ) 2>/dev/null || true'
            } catch(err) {
                throw err
            }
            docker.image("snv-development/service-donvibaocao").run('--name donvibaocao-app-dev --net network-snv-dev --env-file target/docker/env_dev.list')


        }
        // stage ('Run') {
        //     sh "cp -R src/main/docker target/"
        //     sh "cp target/*.war target/docker/"
        //     echo 'I only execute on the development branch'
        //     sh "docker-compose -f docker-compose-dev.yml down"
        //     sh "docker-compose -f docker-compose-dev.yml  build"
        //     sh "docker-compose -f docker-compose-dev.yml  up -d"
        // }
    } catch (e) {
    // If there was an exception thrown, the build failed
    currentBuild.result = "FAILED"
    // failure, always send notifications
    notifyBuild(currentBuild.result)
    throw e
  } finally {

  }
}
def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>"""

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

  // Send notifications
  slackSend (color: colorCode, message: summary)

  hipchatSend (color: color, notify: true, message: summary)

  emailext (
      subject: subject,
      body: details,
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}
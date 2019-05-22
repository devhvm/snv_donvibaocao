#!/usr/bin/env groovy

node {
    stage('Checkout') {
        git url: 'https://github.com/devhvm/snv_phathanhmaubaocao.git', branch: 'development'
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
        dockerImage = docker.build('snv-development/service-phathanhmaubaocao', 'target/docker')
    }
    stage ('Run') {

        sh "cp -R src/main/docker target/"
        sh "cp target/*.war target/docker/"
        //sh "docker system prune -a -f --volumes"

        echo 'I only execute on the development branch'
        try {
            sh '( docker stop phathanhmaubaocao-app-dev > /dev/null && echo Stopped container phathanhmaubaocao-app-dev && \
  docker rm phathanhmaubaocao-app-dev ) 2>/dev/null || true'
        } catch(err) {

        }
        docker.image("snv-development/service-phathanhmaubaocao").run('--name phathanhmaubaocao-app-dev --net network-snv-dev --env-file target/docker/env_dev.list')


    }
    // stage ('Run') {
    //     sh "cp -R src/main/docker target/"
    //     sh "cp target/*.war target/docker/"
    //     echo 'I only execute on the development branch'
    //     sh "docker-compose -f docker-compose-dev.yml down"
    //     sh "docker-compose -f docker-compose-dev.yml  build"
    //     sh "docker-compose -f docker-compose-dev.yml  up -d"
    // }


}

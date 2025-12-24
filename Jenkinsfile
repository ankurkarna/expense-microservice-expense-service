pipeline {
    agent any

    environment {
        IMAGE_NAME = "expense-service:local"
        CLUSTER_NAME = "microservices-lab"
        KIND_NODE = "microservices-lab-control-plane"
        // Use the same internal IP we found earlier
        K8S_SERVER = "https://172.18.0.2:6443"
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh "chmod +x mvnw"
                sh "./mvnw clean package -DskipTests"
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Load Image into Kind') {
            steps {
                // Pipe logic for M2/Kind snapshotter fix
                sh "docker save ${IMAGE_NAME} | docker exec -i ${KIND_NODE} ctr -n k8s.io images import -"
            }
        }

        stage('Deploy to K8s') {
            steps {
                withKubeConfig([credentialsId: 'k8s-config']) {
                    sh "kubectl apply -f expense-service.yaml --server=${K8S_SERVER} --insecure-skip-tls-verify=true --validate=false"
                    sh "kubectl rollout restart deployment expense-service --server=${K8S_SERVER} --insecure-skip-tls-verify=true"
                }
            }
        }
    }
}

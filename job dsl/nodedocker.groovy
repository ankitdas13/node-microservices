job("node js project ver 2"){
    description('This project will clone node js and build and push it to docker hub')
    scm {
        git('https://github.com/ankitdas13/node-microservices.git') { node ->
            node / gitConfigName('ankitdas13')
            node / gitConfigEmail('ankit.das@razorpay.com')
        }
    }
    wrappers { 
        nodejs('node 18')
        credentialsBinding {
           usernamePassword('USERNAME_DOC', 'PASSWORD_DOC', 'dockerhubcred')
        }
    }
    steps {
        shell('npm install')
        shell('docker login -u ${USERNAME_DOC} -p ${PASSWORD_DOC}')
        dockerBuildAndPublish {
            repositoryName('ankitrazorpay/node-jenkins-demo')
            tag('${BUILD_NUMBER}')
            registryCredentials('dockerhubcred')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
       }
       shell('docker logout')
    }
}
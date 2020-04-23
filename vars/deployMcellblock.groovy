#!/usr/bin/env groovy

def getServerPackageVersion(server) {
    def slurper = new JsonSlurper()
    def response = new URL("http://${server}:8081/api/actuator/info").text
    def parsedJson = slurper.parseText(response)
    def serverPackageVersion = parsedJson?.package?.version
    return serverPackageVersion.toString().trim()
}

def call(String server,
         String buildJob = 'mcellblock-master-automatic-build',
         String buildId = 'lastSuccessful',
         boolean newInstall = false,
         boolean waitForCompletion = true) {
    
    //What the package looks like
    def packageFilter = "**/mcellblock-*.tgz"
    
    println "Deploying ${buildJob} ${buildId} to ${server}"
    assert buildId == 'lastSuccessful' || buildId.matches("(\\d+)"
    def buildSelector = buildId == 'lastSuccessful' ? lastSuccessful() : specific(buildId)

    copyArtifacts(
                filter: packageFilter,
                fingerprintArtifacts: true,
                projectName: buildJob,
                selector: buildSelector                
    )

    def pkg = findFiles(glob: packageFilter)[0]
    assert pkg

    //Get the package version from the mcellblock.properties file inside the tarball.
    packageVersion = sh(returnStdout: true, script: "tar -xOf ${pkg} mcellblock.properties |grep packageVersion | cut -d '=' -f2-").trim()
    println "Package to install version is ${packageVersion}"

    def serverPackageVersion = getServerPackageVersion(server)
    println "Server ${params.deploymentServer} has version ${serverPackageVersion}"

    if (newInstall) {
        error("Not implemented")
    } else if (packageVersion != serverPackageVersion) {
        println "Updating the mcellblock application to version ${packageVersion}"

        sh """ curl --request POST \
            --url http://${server}/api/system/update \
            --header 'authorization: Basic c3lzdGVtYWRtaW46TjhNYXJsNWM=' \
            --header 'content-type: multipart/form-data; boundary=---011000010111000001101001' \
            --form file=@${pkg}"""
    } else {
        println "Server ${server} has package ${packageVersion} already installed"
    }

    if (waitForCompletion) {
        timeout(time: 10, unit: 'MINUTES') {
            waitUntil {
                try {
                    def serverPackageVersion = getServerPackageVersion(params.deploymentServer)
                    println "Server version: ${serverPackageVersion}, packageVersion: ${packageVersion}"
                    return packageVersion == serverPackageVersion
                } catch (exception) {
                    println "Error getting server version: " + exception.getMessage()
                    return false
                }
            }
        }
    }
}
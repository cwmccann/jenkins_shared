#!/usr/bin/env groovy

def call(String buildJob = 'mcellblock-master-automatic-build',
         String buildId = 'lastSuccessful') {
    
    def buildSelector = buildId == 'lastSuccessful' ? lastSuccessful() : specific(buildId)

    copyArtifacts(
                filter: "**/mcellblock*.tgz",
                fingerprintArtifacts: true,
                projectName: buildJob,
                selector: buildSelector                
    )
}
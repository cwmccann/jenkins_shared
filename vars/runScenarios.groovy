#!/usr/bin/env groovy

def call(String scenarios, String server, String devices) {
  println "Running scenarios [${scenarios}] on $server with devices [${devices}]"
  
  println "${env.JOB_NAME}"
  println "${env}"

  sh "./gradlew clean scenarioRegression -DbuildName=${mcellblockBuildJobName} -DbuildNumber=${mcellblockBuildJobNumber} \
        -DmcbtestcasesBuildName=${mcbtestcasesJobName} -DmcbtestcasesBuildNumber=${mcbtestcasesJobNumber} \
        -DbuildUrl=${BUILD_URL} -DscenarioTestName=${scenarios} -Ddevices=${devices} -DserverUri=${server}"
}
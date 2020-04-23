#!/usr/bin/env groovy

def call(String scenarios,
         String server,
         String devices,
         String mcbtestcasesJobName = '',
         String mcbtestcasesJobNumber = '') {
  println "Running scenarios [${scenarios}] on $server with devices [${devices}]"

  sh "./gradlew clean scenarioRegression -DbuildName=${env.JOB_NAME} -DbuildNumber=${env.BUILD_ID} \
        -DmcbtestcasesBuildName=${mcbtestcasesJobName} -DmcbtestcasesBuildNumber=${mcbtestcasesJobNumber} \
        -DbuildUrl=${BUILD_URL} -DscenarioTestName=${scenarios} -Ddevices=${devices} -DserverUri=${server}"
}
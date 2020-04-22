#!/usr/bin/env groovy

class Utilities implements Serializable {
  def steps
  
  Utilities(steps) {
    this.steps = steps
  }

  /**
   * Uploads a test module to a server
   */
  def uploadTestModule(server, testModuleFile) {
    println "Upload test module [$testModuleFile] to ${server}"

    steps.sh """ curl \ 
              --request PUT \
              --url http://${server}/api/testModules/upsert \
              --header 'authorization: Basic c3lzdGVtYWRtaW46TjhNYXJsNWM=' \
              --header 'content-type: multipart/form-data;' \
              --form 'file=@${testModuleFile}; type=application/java-archive'
      """
  }
}
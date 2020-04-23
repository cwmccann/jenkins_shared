#!/usr/bin/env groovy
package com.mcb.jenkins

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

    //Run curl in silent mode but grab the headers back so
    //we can check them with grep for failures.
    steps.sh """curl \
              --silent \
              --show-error \
              --include \
              --request PUT \
              --url http://${server}/api/testModules/upsert \
              --header 'authorization: Basic c3lzdGVtYWRtaW46TjhNYXJsNWM=' \
              --header 'content-type: multipart/form-data;' \
              --form 'file=@${testModuleFile}; type=application/java-archive' | grep "HTTP/1.1 500"
      """
  }
}
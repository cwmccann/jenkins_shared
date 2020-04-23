#!/usr/bin/env groovy

def call(server, testModuleFile) {
    println "Upload test module [$testModuleFile] to ${server}"

    //Run curl in silent mode but grab the headers back so
    //we can check them with grep for failures.
    def output = sh (returnStdout: true,
        script: """curl \
                --silent \
                --show-error \
                --include \
                --request PUT \
                --url http://${server}/api/testModules/upsert \
                --header 'authorization: Basic c3lzdGVtYWRtaW46TjhNYXJsNWM=' \
                --header 'content-type: multipart/form-data;' \
                --form 'file=@${testModuleFile}; type=application/java-archive'
        """)

    if (!output.contains("HTTP/1.1 201")) {
        error("Uploading test module ${testModuleFile} to ${server} failed: \n ${output}")
    } else {
        println "Upload successful:\n ${output}"
    }
}
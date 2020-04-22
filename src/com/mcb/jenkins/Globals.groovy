#!/usr/bin/env groovy
package com.mcb.jenkins

class Globals {
    // refer to this in a pipeline using:
    //
    // import com.mcb.jenkins.Globals
    // println Globals.foo
    static String foo = "qux"

    
    static String mcbTestcasesGit = "git@testcases.github.com:miwdesign/mcb-testcases.git"

    static servers = [
        "EPTT1" : "192.168.0.47",
        "EPTT2" : "192.168.0.207",
        "QA01"  : "192.168.1.47",
        "DEV02" : "192.168.1.88",
    ]
}
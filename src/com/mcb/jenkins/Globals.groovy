#!/usr/bin/env groovy
package com.mcb.jenkins

class Globals {
    // refer to this in a pipeline using:
    //
    // import com.mcb.jenkins.Globals
    // println Globals.foo
    static String foo = "qux"       
}
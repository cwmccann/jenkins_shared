#!/usr/bin/env groovy
package com.mcb.jenkins

class Globals {
   static String foo = "bar"

   // refer to this in a pipeline using:
   //
   // import com.mcb.jenkins.Globals
   // println Globals.foo
}
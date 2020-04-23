#!/usr/bin/env groovy
package com.mcb.jenkins

interface EpttConstants {
    static final String epttModuleJar = "**/push-to-talk*.jar"
    
    static final String devicesDescription = """
    Enter in the device you would like to run the tests against. <br>
    The option <b>query</b> will query the deployment server for the device labels and use them.  Or you can specify the list.

    <li><b>EPTT1 (192.168.0.47):</b> eptt_A:988923395134504238,eptt_B:98897a385458434935,eptt_C:9888293936434c4e5a,eptt_D:988820344147414137,eptt_E:5036414149573398,eptt_F:R38M20M0W4H,eptt_G:9889ba374832524e4e</li>
    <li><b>EPTT2 (192.168.0.207):</b> eptt_A:4a504c4a33573398,eptt_B:988820433532324e51,eptt_C:98882945544432574a,eptt_D:9887a8484132314936,eptt_E:9888383743354c5754,eptt_F:RF8M3386HPR,eptt_G:9889a4334d38315135</li>
    """
    
    static final String scenarioDescription = """
    Enter the path to the test(s) you want to run. You only need to supply the path relative to <i>com.mcb.testautomation.test.scenario</i>
    You can supply a package name or a class name and this will run all tests within the package or class, or you can specify a specific test method.<br><br>
    Note: you can use wild card character (*) in the path to represent any package or any class.<br><br>
    Examples:<br>
    <li>Supplies test method name <strong>*.testConferenceCallWithoutHold</strong> will run the test in any class<u>com.mcb.testautomation.test.scenario.*.testConferenceCallWithoutHold</u></li>
    <li>Supplies test method name <strong>McbCallingTest.testConferenceCallWithoutHold</strong> will run the test <u>com.mcb.testautomation.test.scenario.McbCallingTest.testConferenceCallWithoutHold</u></li>
    <li>Supplies package name <strong>dummypackage</strong> will run every test in package <u>com.mcb.testautomation.test.scenario.dummypackage</u></li>
    <li>Supplies class name <strong>McbAnswerSecondCallTest</strong> will run every test in class <u>com.mcb.testautomation.test.scenario.McbAnswerSecondCallTest</u></li>
    """
}
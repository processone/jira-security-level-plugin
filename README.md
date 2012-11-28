jira-security-level-plugin
==========================

This is a JIRA plugin to automatically set security level based on group of the reporter

installation
============

1. Download and install Atlassian SDK (it includes a custom apache-maven manager):

https://developer.atlassian.com/display/DOCS/Install+the+Atlassian+SDK+on+a+Linux+or+Mac+System#InstalltheAtlassianSDKonaLinuxorMacSystem-Step1:DownloadandInstalltheSDK

2. Setup Eclipse project (optional):

2.1. https://developer.atlassian.com/display/DOCS/Set+Up+the+Eclipse+IDE+for+Linux

2.2. Generate Eclipse files on the plugin project

atlas-mvn eclipse:eclipse

2.3. Import the project on your Eclipse IDE

3. Package jar file

atlas-clean
atlas-package

jar files are generated into the ./target directory

Here are the SDK commands you'll use immediately:

* atlas-clean   -- cleans the project
* atlas-package -- generates jar files on ./target directory
* atlas-run     -- installs this plugin into the product and starts it on localhost
* atlas-debug   -- same as atlas-run, but allows a debugger to attach at port 5005
* atlas-cli     -- after atlas-run or atlas-debug, opens a Maven command line window:
                  - 'pi' reinstalls the plugin into the running product instance
* atlas-help    -- prints description for all commands in the SDK

Full documentation is always available at:

https://developer.atlassian.com/display/DOCS/Developing+with+the+Atlassian+Plugin+SDK


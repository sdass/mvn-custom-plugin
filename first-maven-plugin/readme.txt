used by project first-mvn-plgincheck
inside build > ...plugin element this is a dependency

"first" is the plugin name (as per opinionated standard naming convention)
<artifactId>first-maven-plugin</artifactId>
Each goal (hello, hello5, other) are Mojo class that contain execute method
-Dparameter directcly or via indirect reference feed to private variable via setter


Todo:
1. mvn -Dplugin=clean  help:describe works
but why mvn -Dplugin=firs  help:describe gives error (my plugin)
 how to help:describe custom plugin (not a maven plugin). May be I need to add annotated documentation

2. How snapshot mvn plugin will pick up the instant change in
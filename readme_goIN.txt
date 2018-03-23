mvn-custom-plugin outer folder
2 projects [folders] inside. CleanMojo.java is apache's code.
Simple exampl https://dzone.com/articles/a-simple-maven-3-plugin is extended to 3 goals

mvn one custom plugin with 3 simple goals. Read 2 readme files inside each project. plugin jar file generated in first-maven-plugin. And used/consumed by first-mvn-plugincheck application. Result of run tabuated in the readme.

See CleanMojo.java from github for learning. (org.apache.maven.plugins.clean.CleanMojo.java)
https://github.com/apache/maven-clean-plugin/blob/master/src/main/java/org/apache/maven/plugins/clean/CleanMojo.java
How to get mvn source
1. search Maven – Available Plugins - Apache Maven - The Apache Software ...
2. on the page see table clean --GIT/GitHub
on github search clean. See package import org.apache.maven.plugins.clean.CleanMojo;
3. go directory org.apache.maven.plugins\clean
CleanMMojo.java
This depend on first-mvn-plgincheck jar as it is in buld > ...plugin
In all trial below "first" is the plugin name (as per opinionated standard naming convention)
<artifactId>first-maven-plugin</artifactId>
Each goal (hello, hello5, other) are Mojo class that contain execute method
-Dparameter directcly or via indirect reference feed to private variable via setter

without <configuation> element -- setter variable name message
1. mvn first:hello -Dmessage=kkkk
will show in log  Hello kkkk
2. from intellij Plugins drop down -> first-> first:hello
or  mvn first:hello
will show in log Hello from MyMojo

With <configuation> element and passing cmd param
inside <plugin> element
                <configuration>
                    <message>${input}</message>
                </configuration>
1. mvn first:hello -Dinput=kkkk
will show in log  Hello kkkk
2. from intellij Plugins drop down -> first-> first:hello
   or  mvn first:hello
   will show in log Hello arbitrary

----------------------------------------------
mutiple goal in  a single plug-in

mvn clean first:hello5 -Dinput=kkkk555
inside <plugin> element
                <configuration>
                    <message>${input}</message>
                    <message5>${input}TT</message5>
                </configuration>
log shows: Hello 5 kkkk555TT
code: public void execute() throws MojoExecutionException {
             getLog().info("Hello 5 " + message5);
         }

------------------------ 3rd Mojo goal-----
(1)
public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Belongs to " + counry);
    }
    log: [INFO] Belongs to United States

(2)
instead of adding configuration parameter I pass as -Dproperty=val
mvn -Dcountry=Sweden first:other
log: [INFO] Belongs to Sweden
-------------------
side note to understand confusion of intellij
build with version number 1.0.1, etc., instead of snapshot until figure out where from
intellij GUI is caching the plugin jar from. Minor because cmdline works.
Plugins->first->* does not show newer one unless version changed.



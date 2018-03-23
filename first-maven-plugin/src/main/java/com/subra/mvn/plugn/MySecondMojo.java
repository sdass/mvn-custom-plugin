package com.subra.mvn.plugn;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by sdass on 3/23/2018.
 */
@Mojo(name="other")
public class MySecondMojo extends AbstractMojo{

    @Parameter(property = "country", defaultValue = "United States")
    private String counry;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Belongs to " + counry);
    }

    public String getCounry() {
        return counry;
    }

    public void setCounry(String counry) {
        this.counry = counry;
    }
}

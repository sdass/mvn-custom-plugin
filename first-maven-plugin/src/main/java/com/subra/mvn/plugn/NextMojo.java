package com.subra.mvn.plugn;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name="hello5")
public class
NextMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */

    // private File outputDirectory;

    @Parameter(property = "message5", defaultValue = "from MyMojo5")
    private String message5;

    public void execute() throws MojoExecutionException {
        getLog().info("Hello 5 " + message5);

    }

    public String getMessage() {
        return message5;
    }

    public void setMessage(String message) {
        this.message5 = message;
    }
    /*
    public void execute() throws MojoExecutionException
    {
        File f = outputDirectory;

        if ( !f.exists() )
        {
            f.mkdirs();
        }

        File touch = new File( f, "touch.txt" );

        FileWriter w = null;
        try
        {
            w = new FileWriter( touch );

            w.write( "touch.txt" );
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating file " + touch, e );
        }
        finally
        {
            if ( w != null )
            {
                try
                {
                    w.close();
                }
                catch ( IOException e )
                {
                    // ignore
                }
            }
        }
    }
*/

}


https://github.com/apache/maven-clean-plugin/blob/master/src/main/java/org/apache/maven/plugins/clean/CleanMojo.java
package org.apache.maven.plugins.clean;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;

@Mojo( name = "clean", threadSafe = true )
public class CleanMojo
    extends AbstractMojo
{

    /**
     * This is where build results go.
     */
    @Parameter( defaultValue = "${project.build.directory}", readonly = true, required = true )
    private File directory;

    /**
     * This is where compiled classes go.
     */
    @Parameter( defaultValue = "${project.build.outputDirectory}", readonly = true, required = true )
    private File outputDirectory;

    /**
     * This is where compiled test classes go.
     */
    @Parameter( defaultValue = "${project.build.testOutputDirectory}", readonly = true, required = true )
    private File testOutputDirectory;

    /**
     * This is where the site plugin generates its pages.
     *
     * @since 2.1.1
     */
    @Parameter( defaultValue = "${project.build.outputDirectory}", readonly = true, required = true )
    private File reportDirectory;


    @Parameter( property = "maven.clean.verbose" )
    private Boolean verbose;


    @Parameter
    private Fileset[] filesets;

    @Parameter( property = "maven.clean.followSymLinks", defaultValue = "false" )
    private boolean followSymLinks;

    @Parameter( property = "maven.clean.skip", defaultValue = "false" )
    private boolean skip;

    @Parameter( property = "maven.clean.failOnError", defaultValue = "true" )
    private boolean failOnError;

    @Parameter( property = "maven.clean.retryOnError", defaultValue = "true" )
    private boolean retryOnError;

    @Parameter( property = "maven.clean.excludeDefaultDirectories", defaultValue = "false" )
    private boolean excludeDefaultDirectories;

    /**
     * Deletes file-sets in the following project build directory order: (source) directory, output directory, test
     * directory, report directory, and then the additional file-sets.
     *
     * @throws MojoExecutionException When a directory failed to get deleted.
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute()
        throws MojoExecutionException
    {
        if ( skip )
        {
            getLog().info( "Clean is skipped." );
            return;
        }

        Cleaner cleaner = new Cleaner( getLog(), isVerbose() );

        try
        {
            for ( File directoryItem : getDirectories() )
            {
                if ( directoryItem != null )
                {
                    cleaner.delete( directoryItem, null, followSymLinks, failOnError, retryOnError );
                }
            }

            if ( filesets != null )
            {
                for ( Fileset fileset : filesets )
                {
                    if ( fileset.getDirectory() == null )
                    {
                        throw new MojoExecutionException( "Missing base directory for " + fileset );
                    }
                    GlobSelector selector = new GlobSelector( fileset.getIncludes(), fileset.getExcludes(),
                                                              fileset.isUseDefaultExcludes() );
                    cleaner.delete( fileset.getDirectory(), selector, fileset.isFollowSymlinks(), failOnError,
                                    retryOnError );
                }
            }
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Failed to clean project: " + e.getMessage(), e );
        }
    }

    private boolean isVerbose()
    {
        return ( verbose != null ) ? verbose : getLog().isDebugEnabled();
    }

    private File[] getDirectories()
    {
        File[] directories;
        if ( excludeDefaultDirectories )
        {
            directories = new File[0];
        }
        else
        {
            directories = new File[] { directory, outputDirectory, testOutputDirectory, reportDirectory };
        }
        return directories;
    }

}

package nmezdari.iut;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

public class HelloWorld extends AbstractMojo {
	public void execute() throws MojoExecutionException {
		getLog().info("HelloWorldNiceWeatherIsntIt?");
	}
}
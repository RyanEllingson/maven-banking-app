package launch;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

	public static void main(String[] args) throws Exception {

		String webappDirLocation = "src/main/webapp/";
		Tomcat tomcat = new Tomcat();
		
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		
		tomcat.setPort(Integer.valueOf(webPort));
		
		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
		
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);
		
		tomcat.start();
		tomcat.getServer().await();
	}

}

package sample.htmx;

import rife.bld.WebProject;

import java.util.List;

import static rife.bld.dependencies.Repository.*;
import static rife.bld.dependencies.Scope.*;
import static rife.bld.operations.TemplateType.*;

public class TodoappBuild extends WebProject {
    public TodoappBuild() {
        pkg = "sample.htmx";
        name = "Todoapp";
        mainClass = "sample.htmx.TodoappSite";
        uberJarMainClass = "sample.htmx.TodoappSiteUber";
        version = version(0, 1, 0);

        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);
        scope(compile)
                .include(dependency("com.uwyn.rife2:rife2:1.7.3"))
                .include(dependency("org.slf4j:slf4j-simple:2.0.13"));
        scope(provided)
                .include(dependency("com.h2database:h2:2.2.224"));
        scope(test)
                .include(dependency("org.jsoup:jsoup:1.17.2"))
                .include(dependency("org.junit.jupiter:junit-jupiter:5.10.2"))
                .include(dependency("org.junit.platform:junit-platform-console-standalone:1.10.2"));
        scope(standalone)
                .include(dependency("org.eclipse.jetty.ee10:jetty-ee10-servlet:12.0.8"))
                .include(dependency("org.eclipse.jetty.ee10:jetty-ee10:12.0.8"));

        precompileOperation()
                .templateTypes(HTML);
    }

    public static void main(String[] args) {
        new TodoappBuild().start(args);
    }
}

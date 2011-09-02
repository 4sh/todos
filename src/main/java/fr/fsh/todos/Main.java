package fr.fsh.todos;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import jewas.http.RestServer;
import jewas.http.RestServerFactory;
import jewas.routes.RedirectRoute;
import jewas.routes.SimpleFileRoute;
import jewas.routes.SimpleHtmlRoute;
import jewas.routes.StaticResourcesRoute;

/**
 * @author fcamblor
 */
public class Main {
    public static void main(String[] args){
        CliOptions options = new CliOptions();
        try {
            JCommander jcommander = new JCommander(options, args);
        }catch(ParameterException e){
            System.err.println(e.getMessage());
            new JCommander(options).usage();
            System.exit(-1);
        }

        final RestServer rs = RestServerFactory.createRestServer(options.httpPort());
        rs.addRoutes(
                new RedirectRoute("/", "/hello.html"),
                // Not really a static resource (located in webapp folder) since it is provided
                // by jewas library. So it must be declared before the StaticResourcesRoute !
                new SimpleFileRoute("/public/js/jewas/jewas-forms.js", "js/jewas-forms.js"),
                new StaticResourcesRoute("/public/", "public/"),
                new SimpleHtmlRoute("/hello.html", "hello/hello.ftl")
        ).start();
        System.out.println("Ready, if you dare");
    }
}

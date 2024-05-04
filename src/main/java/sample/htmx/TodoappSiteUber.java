package sample.htmx;

import rife.engine.Server;

public class TodoappSiteUber extends TodoappSite {
    public static void main(String[] args) {
        new Server()
            .staticUberJarResourceBase("webapp")
            .start(new TodoappSiteUber());
    }
}
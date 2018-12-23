package server;



import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class RunServer {

  public static void main(String[] args) {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    context.setContextPath("/");

    Server jettyServer = new Server(9999);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");
    jerseyServlet.setInitOrder(0);

    // Tells Jersey in what package to look for the classes.
    jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "jwtdemo");

    try {
      jettyServer.start();
      jettyServer.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jettyServer.destroy();
    }
  }
}
  
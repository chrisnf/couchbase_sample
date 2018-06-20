import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // Http-Server starten
        HttpServer server = null;
        ResourceConfig rc = new ResourceConfig(ProductResource.class);
        URI endpoint = new URI("http://localhost:8080/rest");
        server = JdkHttpServerFactory.createHttpServer(endpoint, rc);
    }
}

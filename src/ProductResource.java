import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("product")
public class ProductResource
{
    @GET
    @Path("search/{text}")
    @Produces(MediaType.TEXT_PLAIN)
    public String search(@PathParam("text") String text)
    {
        return DBConnector.getInstance().searchProductByName(text);
    }
}
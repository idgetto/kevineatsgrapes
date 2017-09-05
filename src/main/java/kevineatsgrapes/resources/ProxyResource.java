package kevineatsgrapes.resources;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;

@Path("/analytics")
@Produces(MediaType.TEXT_PLAIN)
public class ProxyResource {
  private final HttpClient httpClient;
  private final BasicResponseHandler basicResponseHandler;

  public ProxyResource(HttpClient httpClient) {
    this.httpClient = httpClient;
    this.basicResponseHandler = new BasicResponseHandler();
  }

  @GET
  @Path("/r/collect")
  public Response send(@Context HttpServletRequest request) throws IOException {
    String url = "http://www.google-analytics.com/analytics/r/collect/?" + request.getQueryString();
    HttpGet httpGet = new HttpGet(url);

    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String name = headerNames.nextElement();
      String value = request.getHeader(name);
      httpGet.setHeader(name, value);
    }

    System.out.println(httpGet);
    System.out.println(httpGet.getAllHeaders());
    for (Header header : httpGet.getAllHeaders()) {
      System.out.println(header.getName() + " : " + header.getValue());
    }

    httpClient.execute(httpGet);
    return Response.noContent().build();
  }
}

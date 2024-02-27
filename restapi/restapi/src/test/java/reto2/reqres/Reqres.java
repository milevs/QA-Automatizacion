package reto2.reqres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.JsonSchemaValidator;

public class Reqres {

    HttpClient clientHttp;

    @BeforeEach
    public void setup(){
        System.out.println("Inicio de la Configuracion de la Prueba REST API");
        clientHttp = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }

    @Test
    public void usersPage2() throws URISyntaxException, IOException, InterruptedException{

        URIBuilder uri = new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/users")
            .addParameter("page", "2");
      

        System.out.println("Endpoint: " + uri.build());

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(uri.build())
            .build();

        HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.body().contains("michael.lawson@reqres.in"));

        String validationSchema =  JsonSchemaValidator.validateJsonAgainstSchema(response.body(), "UsersPage12Schema.json");

        System.out.println("Resultado de la validacion del Schema e los users de la pagina 2: " + validationSchema);

        assertEquals("", validationSchema, "Resultado de la validacion del schema de los users de la pagina 2");
    }
    @Test
    public void dataUser12() throws URISyntaxException, IOException, InterruptedException{

        URIBuilder uri = new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/users")
            .addParameter("id", "12");
      

        System.out.println("Endpoint: " + uri.build());

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(uri.build())
            .build();

        HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.body().contains("rachel.howell@reqres.in"));

        String validationSchema =  JsonSchemaValidator.validateJsonAgainstSchema(response.body(), "User12Schema.json");

        System.out.println("Resultado de la validacion del Schema de user ID 12: " + validationSchema);

     assertEquals("", validationSchema, "Resultado de la validacion de user ID 12");
    }

    @Test
    public void dataUserUnexistent() throws URISyntaxException, IOException, InterruptedException{

        URIBuilder uri = new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/users")
            .addParameter("id", "855");
      

        System.out.println("Endpoint: " + uri.build());

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(uri.build())
            .build();

        HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
        assertTrue(response.body().contains(""));

        String validationSchema =  JsonSchemaValidator.validateJsonAgainstSchema(response.body(), "UnexistentUserSchema.json");

        System.out.println("Resultado de la validacion del Schema para un usuario no existente: " + validationSchema);

        assertEquals("", validationSchema, "Resultado de la validacion del schema para un usuario no existente");
    }
    @Test
    public void unknow() throws URISyntaxException, IOException, InterruptedException{

        URIBuilder uri = new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/unknown");
           
      

        System.out.println("Endpoint: " + uri.build());

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(uri.build())
            .build();

        HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.body().contains("fuchsia rose"));

        String validationSchema =  JsonSchemaValidator.validateJsonAgainstSchema(response.body(), "UnknownSchema.json");

        System.out.println("Resultado de la validacion del Unknown Schema: " + validationSchema);

        assertEquals("", validationSchema, "Resultado de la validacion del schema para Unknown");
    }

    @Test
    public void delayedResponse() throws URISyntaxException, IOException, InterruptedException{

        URIBuilder uri = new URIBuilder()
            .setScheme("https")
            .setHost("reqres.in")
            .setPath("api/users")
            .addParameter("delay", "3");
      

        System.out.println("Endpoint: " + uri.build());

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(uri.build())
            .build();

        HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.body().contains("george.bluth@reqres.in"));

        String validationSchema =  JsonSchemaValidator.validateJsonAgainstSchema(response.body(), "DelayedResponseSchema.json");

        System.out.println("Resultado de la validacion del Schema Delayed Response: " + validationSchema);

        
        assertEquals("", validationSchema, "Resultado de la validacion del Schema Delayed Response");
    }
}

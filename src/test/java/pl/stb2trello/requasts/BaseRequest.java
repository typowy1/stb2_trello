package pl.stb2trello.requasts;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    protected static RequestSpecBuilder requestBuilder;

//    ustawiamy części wspólne dla wszystkich requestów. content type, klucz, token

    public static RequestSpecification requestSetup() { // bęzie zwracać specyfikacje dla naszego requestu

        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addQueryParam("key", TrelloSecrets.getKey());
        requestBuilder.addQueryParam("token", TrelloSecrets.getToken());
        requestBuilder.addFilter(new RequestLoggingFilter()); // mega ! zarejestruje nam na console wszystkie informacje requestu ktore wysyłamy
        requestBuilder.addFilter(new ResponseLoggingFilter()); // mega ! zarejestruje nam na console wszystkie informacje response ktore dostajemy
        return requestBuilder.build();
    }
}

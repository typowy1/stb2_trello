package pl.stb2trello.requasts.List;

import io.restassured.response.Response;
import pl.stb2trello.requasts.BaseRequest;
import pl.stb2trello.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createListRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup()) // to nam ustawi content type, key, token
                .queryParams(queryParams) // tak możemy dodać więcej parametrów
                .when()
                .post(TrelloUrl.getListsUrl())
                .then()
                .extract()
                .response();
    }
}

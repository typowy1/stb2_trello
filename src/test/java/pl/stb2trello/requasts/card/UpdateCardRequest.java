package pl.stb2trello.requasts.card;

import io.restassured.response.Response;
import pl.stb2trello.requasts.BaseRequest;
import pl.stb2trello.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {

    public static Response updateCardRequest(Map<String, String> queryParams, String cardId) {
        return given()
                .spec(BaseRequest.requestSetup()) // to nam ustawi content type, key, token
                .queryParams(queryParams) // tak możemy dodać więcej parametrów
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .extract()
                .response();
    }
}

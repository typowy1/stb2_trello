package pl.stb2trello.requasts.board;

import io.restassured.response.Response;
import pl.stb2trello.requasts.BaseRequest;
import pl.stb2trello.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {

    public static Response createBoardRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup()) // to nam ustawi content type, key, token
                .queryParams(queryParams) // tak możemy dodać więcej parametrów
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}

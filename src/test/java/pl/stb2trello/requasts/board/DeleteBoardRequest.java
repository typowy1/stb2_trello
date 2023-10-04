package pl.stb2trello.requasts.board;

import io.restassured.response.Response;
import pl.stb2trello.requasts.BaseRequest;
import pl.stb2trello.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {

    public static Response deleteBoardRequest(String boardId) {
        return given()
                .spec(BaseRequest.requestSetup()) // to nam ustawi content type, key, token
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}

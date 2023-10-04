package pl.stb2trello.tests.board.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.stb2trello.requasts.List.CreateListRequest;
import pl.stb2trello.requasts.board.CreateBoardRequest;
import pl.stb2trello.requasts.board.DeleteBoardRequest;
import pl.stb2trello.requasts.card.CreateCardRequest;
import pl.stb2trello.requasts.card.UpdateCardRequest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoveCardBetweenList {

    private final String boardName = "Tablica do testu e2e";
    private final String firstListName = "First list";
    private final String secondListName = "Second list";
    private final String cardName = "My first card";

    private static String boardId; // jeżeli pola sa metodach nie muszą być statyczne, jeżeli sa w metodach oznaczonych test to musza być statyczne
    private static String firstListId;
    private static String secondListId;
    private static String cardId;


//    junit nie puszcza testów w kolejności więc możemy to zrobić na dwa sposoby 1 tworzymy 1 test i do nie go wrzucamy metidy ktore eprezentują poszczególne kroki

//    void scenarioE2ETest(){ // tylko metody w tej metodzie muszą byc bez adnotacji @Test
//        createNewBoardTest();
//        createFirstList();
//        createSecondList();
//    }

//     lub ddodajemy kolejną adnotacje ORDERi nad klasa adnotacje @TestMethodOrder(MethodOrderer.OrderAnnotation.class), plus łatwiej zdebugować, mamy liste testow

    @Test
    @Order(1)
    void createNewBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);
        assertThat(createBoardResponse.statusCode()).isEqualTo(200);

        JsonPath jsonPath = createBoardResponse.jsonPath();
        assertThat(jsonPath.getString("name")).isEqualTo(boardName);
        boardId = jsonPath.getString("id");
    }

    @Test
    @Order(2)
    void createFirstList() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response response = CreateListRequest.createListRequest(queryParams);
        assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("name")).isEqualTo(firstListName);
        firstListId = jsonPath.getString("id");
    }

    @Test
    @Order(3)
    void createSecondList() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", secondListName);
        queryParams.put("idBoard", boardId);

        final Response response = CreateListRequest.createListRequest(queryParams);
        assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("name")).isEqualTo(secondListName);
        secondListId = jsonPath.getString("id");
    }

    @Test
    @Order(4)
    void createCardOnFirstListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", firstListId);
        queryParams.put("name", cardName);

        final Response response = CreateCardRequest.createCardRequest(queryParams);
        assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("name")).isEqualTo(cardName);
        cardId = jsonPath.getString("id");
    }

    @Test
    @Order(5)
    void moveCardToSecondListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", secondListId);


        Response response = UpdateCardRequest.updateCardRequest(queryParams, cardId);
        assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("idList")).isEqualTo(secondListId);
    }

    @Test
    @Order(6)
    void deleteBoardTest(){
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}

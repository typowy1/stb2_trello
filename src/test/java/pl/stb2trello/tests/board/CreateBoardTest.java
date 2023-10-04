package pl.stb2trello.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.stb2trello.requasts.board.CreateBoardRequest;
import pl.stb2trello.requasts.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CreateBoardTest {

    private String boardId;

    //    test wykona się tyle razy ile jest danych w metodzie sampleBoardNameData
    @DisplayName("Create board with valid data")
    @ParameterizedTest(name = "Board name: {0}")
    // test parametryzowany, nazwa wyświetlana dla każdego przypadku, {0} w tym miejscu wyswietla sie dane z metody
    @MethodSource("sampleBoardNameData")
    // żródłem danych dla naszego testu będzie metoda sampleBoardNameData
    void createBoardTest(String boardName) { // dane z metody wejdą tu

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

//        createBoard
        final Response response = CreateBoardRequest.createBoardRequest(queryParams);
        assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("name")).isEqualTo(boardName);
        boardId = jsonPath.getString("id");

//        deleteBoard
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    private static Stream<Arguments> sampleBoardNameData() { // metoda z danymi do testów
        return Stream.of(
                Arguments.of("nazwaTablicy"),
                Arguments.of("NAZWATABLICY"),
                Arguments.of("NAZWA_TABLICY"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&")
        );

    }
}

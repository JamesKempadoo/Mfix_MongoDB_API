package com.sparta.academy.mfix_mongodb_api.framework.framework_test.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.model.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConnectionManagerTests {

    private static ConnectionManager connectionManager;
    private static final String testURL = "http://test.test";

    @BeforeEach
    void setup() {
        connectionManager = ConnectionManager.from();
    }

    @Test
    @DisplayName("Test that the from() method returns a new ConnectionManager Object")
    void testFrom() {
        assertThat(ConnectionManager.from(), instanceOf(ConnectionManager.class));
    }

    @Test
    @DisplayName("Test that the baseURL() method sets the internal URL to the base URL")
    void testBaseURL() {
        connectionManager.baseURL();
        assertThat(connectionManager.getURL(), equalTo(ConnectionManager.BASE_URL));
    }

    @Test
    @DisplayName("Test that the URL() method sets the internal URL to the passed string")
    void testURL() {
        connectionManager.URL(testURL);
        assertThat(connectionManager.getURL(), equalTo(testURL));
    }

    @Test
    @DisplayName("Test that the slash() method adds the given path to the URL")
    void testSlash() {
        connectionManager.URL(testURL).slash("test");
        assertThat(connectionManager.getURL(), equalTo(testURL + "/test"));
    }

    @Test
    @DisplayName("Test that the withParameter() method adds the given path variable to the map")
    void testWithParameter() {
        connectionManager.URL(testURL).withParameter("key", "value");
        assertThat(connectionManager.getParameters(), hasEntry("key", "value"));
    }

    @ParameterizedTest
    @CsvSource({
            "GET, GET",
            "POST, POST",
            "patch, PATCH",

    })
    @DisplayName("Test that the usingMethod() method sets the used method to the given value")
    void testUseMethod(String given, String expected) {
        connectionManager.usingMethod(given);
        assertThat(connectionManager.getMethod(), equalTo(expected));
    }

    @Test
    @DisplayName("Test that the withBody() method sets the body of the request to a given string")
    void testWithBodyString() {
        connectionManager.withBody("BODY");
        assertThat(connectionManager.getBody(), equalTo("BODY"));
    }

    @ParameterizedTest
    @MethodSource("getValidJSON")
    @DisplayName("Test that the withBody() methods sets the body of the request to a JSON string" +
            "of the given object")
    void testWithBodyObject(String json) {
        connectionManager.withBody(user);
        assertThat(connectionManager.getBody(), equalTo(json));
    }

    @Mock(serializable = true)
    private static final User user = Mockito.mock(User.class);

    private static Stream<String> getValidJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return Stream.of(mapper.writeValueAsString(user));
    }

    @Nested
    class TestGetResponse {

        private static ArgumentCaptor<String> passedURL;
        private static ArgumentCaptor<String> passedMethod;
        private static ArgumentCaptor<String> passedBody;

        @BeforeAll
        static void setupAll() {
            MockedConstruction<ConnectionResponse> mockedResponse
                    = Mockito.mockConstruction(ConnectionResponse.class);
            passedURL = ArgumentCaptor.forClass(String.class);
            passedMethod = ArgumentCaptor.forClass(String.class);
            passedBody = ArgumentCaptor.forClass(String.class);

            ConnectionManager.from()
                    .URL(testURL)
                    .withParameter("key", "value")
                    .withBody("BODY")
                    .usingMethod("POST")
                    .getResponse();

            Mockito.verify(mockedResponse.constructed().get(0))
                    .makeRequest(passedURL.capture(), passedMethod.capture(), passedBody.capture());
        }

        @Test
        @DisplayName("Test that the getResponse() method creates a ConnectionResponse with the correct URL")
        void testGetResponseURL() {
            assertThat(passedURL.getValue(), equalTo("http://test.test?key=value"));
        }

        @Test
        @DisplayName("Test that the getResponse() method creates a ConnectionResponse with the correct Method")
        void testGetResponseMethod() {
            assertThat(passedMethod.getValue(), equalTo("POST"));
        }

        @Test
        @DisplayName("Test that the getResponse() method creates a ConnectionResponse with the correct Body")
        void testGetResponseBody() {
            assertThat(passedBody.getValue(), equalTo("BODY"));
        }
    }
}

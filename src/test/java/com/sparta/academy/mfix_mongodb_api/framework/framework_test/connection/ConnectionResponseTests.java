package com.sparta.academy.mfix_mongodb_api.framework.framework_test.connection;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.comments.CommentDTO;
import com.sparta.academy.mfix_mongodb_api.framework.exception.ConnectionManagementException;
import com.sparta.academy.mfix_mongodb_api.framework.injector.Injector;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionResponseTests {

    private final Integer[] validStatusCodes = {200, 201, 200, 201, 202, 203, 204, 205, 206, 207, 208, 226,
            300, 301, 302, 303, 304, 305, 306, 307, 308, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409,
            410, 411, 412, 413, 414, 415, 416, 417, 418, 420, 422, 423, 424, 425, 426, 428, 429, 431, 444,
            449, 450, 451, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 598, 599};
    private ConnectionResponse response;

    @BeforeEach
    void setup() {
        response = ConnectionManager.from().baseURL().getResponse();
    }

    @Test
    @DisplayName("Test that attempting to make a request with an invalid url throws an exception")
    void testMakeRequestInvalidURL() {
        assertThrows(ConnectionManagementException.class, ()
                -> response.makeRequest("", "GET", ""));
    }

    @Test
    @DisplayName("Test that attempting to make a request with an invalid HTTP Method throws an exception")
    void testMakeRequestInvalidMethod() {
        assertThrows(ConnectionManagementException.class, ()
                -> response.makeRequest(ConnectionManager.BASE_URL, "HEAD", ""));
    }

    @Test
    @DisplayName("Test that a header can be retrieved from a request")
    void testGetHeader() {
        assertThat(response.getHeader("Server"), notNullValue());
    }

    @Test
    @DisplayName("Test that a valid status code can be retrieved from a request")
    void testGetStatusCode() {
        assertThat(validStatusCodes, hasItemInArray(response.getStatusCode()));
    }

    @Nested
    class TestHTTPMethods {

        private static final HttpRequest.Builder mockBuilder = Mockito.mock(HttpRequest.Builder.class);
        private static MockedStatic<HttpRequest> mockRequest;

        @BeforeAll
        static void setupAll() throws URISyntaxException {
            mockRequest = Mockito.mockStatic(HttpRequest.class);
            mockRequest.when(HttpRequest::newBuilder).thenReturn(mockBuilder);
            Mockito.when(mockBuilder.uri(new URI(ConnectionManager.BASE_URL))).thenReturn(mockBuilder);
            Mockito.when(mockBuilder.header("Content-Type", "application/json"))
                    .thenReturn(mockBuilder);
        }

        @AfterAll
        static void teardownAll() {
            mockRequest.close();
        }

        @Test
        @DisplayName("Test the GET method is used when requested")
        void testGET() {
            response.makeRequest(ConnectionManager.BASE_URL, "GET", "");
            Mockito.inOrder(mockBuilder).verify(mockBuilder).GET();
        }

        @Test
        @DisplayName("Test the DELETE method is used when requested")
        void testDELETE() {
            response.makeRequest(ConnectionManager.BASE_URL, "DELETE", "");
            Mockito.inOrder(mockBuilder).verify(mockBuilder).DELETE();
        }

        @Test
        @DisplayName("Test the PUT method is used when requested")
        void testPUT() {
            response.makeRequest(ConnectionManager.BASE_URL, "PUT", "");
            Mockito.inOrder(mockBuilder).verify(mockBuilder).PUT(Mockito.any());
        }

        @Test
        @DisplayName("Test the POST method is used when requested")
        void testPOST() {
            response.makeRequest(ConnectionManager.BASE_URL, "POST", "");
            Mockito.inOrder(mockBuilder).verify(mockBuilder).POST(Mockito.any());
        }

        @Test
        @DisplayName("Test the PATCH method is used when requested")
        void testPATCH() {
            response.makeRequest(ConnectionManager.BASE_URL, "PATCH", "");
            Mockito.inOrder(mockBuilder).verify(mockBuilder)
                    .method(Mockito.eq("PATCH"), Mockito.any());
        }
    }

    @Nested
    class TestBody {

        private static MockedStatic<Injector> injector;
        private ArgumentCaptor<String> passedString;
        private ArgumentCaptor<Class> passedClass;
        private CommentDTO mockDTO;

        @BeforeEach
        void setup() {
            injector = Mockito.mockStatic(Injector.class);
            passedString = ArgumentCaptor.forClass(String.class);
            passedClass = ArgumentCaptor.forClass(Class.class);
            mockDTO = Mockito.mock(CommentDTO.class);
        }

        @AfterEach
        void teardownAll() {
            injector.close();
        }

        @Test
        @DisplayName("Test that when a body is requested the correct JSON is passed to injector")
        void testGetBodyJSON() {
            response.getBodyAs(mockDTO.getClass());
            injector.verify(() -> Injector.getDTO(passedString.capture(), passedClass.capture()));

            assertTrue(isValidJSON(passedString.getValue()));
        }

        @Test
        @DisplayName("Test that when a body is requested the correct class is passed to injector")
        void testGetBodyClass() {
            response.getBodyAs(mockDTO.getClass());
            injector.verify(() -> Injector.getDTO(passedString.capture(), passedClass.capture()));

            assertThat(passedClass.getValue(), equalTo(mockDTO.getClass()));
        }

        @Test
        @DisplayName("Test that when an array of bodies is requested the correct JSON is passed to injector")
        void testGetBodyAsArrayJSON() {
            response.getBodyAsArrayOf(mockDTO.getClass());
            injector.verify(() -> Injector.getDTO(passedString.capture(), passedClass.capture()));

            assertTrue(isValidJSON(passedString.getValue()));
        }

        @Test
        @DisplayName("Test that when an array of bodies is requested the correct class is passed to injector")
        void testGetBodyAsArrayClass() {
            response.getBodyAsArrayOf(mockDTO.getClass());
            injector.verify(() -> Injector.getDTO(passedString.capture(), passedClass.capture()));

            assertThat(passedClass.getValue(), equalTo(mockDTO.getClass().arrayType()));
        }

        private boolean isValidJSON(String json) {
            try {
                new ObjectMapper().readTree(json);
            } catch (JacksonException e) {
                return false;
            }
            return true;
        }

    }
}
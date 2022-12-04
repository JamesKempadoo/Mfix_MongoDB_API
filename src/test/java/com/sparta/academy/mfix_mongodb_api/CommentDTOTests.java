package com.sparta.academy.mfix_mongodb_api;

import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.services.MockMFlixApplication;
import com.sparta.academy.mfix_mongodb_api.model.entity.Comment;
import com.sparta.academy.mfix_mongodb_api.repositories.CommentRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.UserRepository;
import org.junit.jupiter.api.*;
import com.sparta.academy.mfix_mongodb_api.framework.dto.comments.CommentDTO;
import org.mockito.Mockito;

import java.util.List;


class CommentDTOTests {

    private static ConnectionManager connectionManager;

    private static ConnectionResponse connectionResponse;

    private static CommentDTO expectedDTO1;

    private static CommentDTO[] expectedCommentsDTOArray;

    private static CommentRepository mockedRepo;

    private static UserRepository userMockedRepo;


    private static List<Comment> mockRepoResultForOne;

    private static Comment comment1;


    @BeforeAll
    static void setup() {

        MockMFlixApplication.run();

        mockedRepo = MockMFlixApplication.getCommentRepository();

        userMockedRepo = MockMFlixApplication.getUserRepository();
    }

    @BeforeEach
    void setupConnection() {

        // Dummy Comments:
        comment1 = new Comment("Fri May 03 01:58:19 BST 1974", "user one", "email1@email.com", "573a1391f29313caabcd80db1", "THIS IS A COMMENT 1");
        comment1.setId("id1");
        Mockito.when(mockedRepo.findCommentById("id1")).thenReturn(comment1);

        Comment comment2 = new Comment("Sat May 04 01:58:19 BST 1975", "user two", "email1@email.com", "573a1391f29313caabcd80db2", "THIS IS A COMMENT 2");
        comment2.setId("id2");
        Mockito.when(mockedRepo.findCommentById("id2")).thenReturn(comment2);

        Comment comment3 = new Comment("Sun May 05 01:58:19 BST 1976", "user three", "email1@email.com", "573a1391f29313caabcd80db3", "THIS IS A COMMENT 3");
        comment3.setId("id3");
        Mockito.when(mockedRepo.findCommentById("id3")).thenReturn(comment3);

        // Expected DTO for Comment 1
        expectedDTO1 = new CommentDTO("id1", "user one", "Fri May 03 01:58:19 BST 1974", "email1@email.com", "573a1391f29313caabcd80db1", "THIS IS A COMMENT 1");

        expectedCommentsDTOArray = new CommentDTO[]{expectedDTO1};

        // Repo Response with 1 RESULT
        mockRepoResultForOne = List.of(comment1);

        // Repo Response for ALL COMMENTS
        List<Comment> mockRepoResultForAll = List.of(comment1, comment2, comment3);
        Mockito.when(mockedRepo.findAll()).thenReturn(mockRepoResultForAll);

        // Connection Manager with BASE_URL/comments
        connectionManager = ConnectionManager.from().baseURL().slash("comments");
    }

    @Nested
    @DisplayName("comments/all Tests")
    class GetHeaderTests {


        @Test
        @DisplayName("Test that /all endpoints returns 3 results")
        void getAllMockedComments() {
            // Connection Manager get Response
            connectionResponse = connectionManager.slash("all").getResponse();
            CommentDTO[] listOfDto = connectionResponse.getBodyAsArrayOf(CommentDTO.class);

            Assertions.assertEquals(3, listOfDto.length);
        }


        @Test
        @DisplayName("Test Header Status is 200")
        void testHeaderStatusIs200() {

            connectionResponse = connectionManager.slash("all").getResponse();
            Assertions.assertEquals(200, connectionResponse.getStatusCode());

        }

        @Test
        @DisplayName("Test Header Status is 404")
        void testHeaderStatusIs404() {

            connectionResponse = connectionManager.slash("all/a").getResponse();
            Assertions.assertEquals(404, connectionResponse.getStatusCode());

        }


        @Test
        @DisplayName("Test Header Data Type is JSON")
        void testHeaderAllowedDataTypeIsJson() {

            connectionResponse = connectionManager.slash("all").getResponse();
            Assertions.assertEquals("application/json", connectionResponse.getHeader("Content-Type"));

        }

    }


    @Nested
    @DisplayName("GET single comment DTO Tests")
    class GETSingleCommentTests {


        @Test
        @DisplayName("Test get comment with valid ID")
        void getCommentById() {

            Mockito.when(mockedRepo.existsById("id1")).thenReturn(true);

            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").getResponse();
            CommentDTO dto = connectionResponse.getBodyAs(CommentDTO.class);

            Assertions.assertEquals(expectedDTO1, dto);

        }

        @Test
        @DisplayName("Test Comment DTO 1 has valid data")
        void getCommentById1HasValidDate() {

            Mockito.when(mockedRepo.existsById("id1")).thenReturn(true);

            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").getResponse();
            CommentDTO dto = connectionResponse.getBodyAs(CommentDTO.class);

            Assertions.assertTrue(dto.isCommentDTOFieldsValid() && dto.isDateValid() && dto.isEmailValid());

        }

        @Test
        @DisplayName("Test Response Status from GET comment with INVALID ID")
        void getCommentWithInvalidID() {

            Mockito.when(mockedRepo.existsById("6")).thenReturn(false);
            ConnectionResponse connectionResponse = connectionManager.slash("id/6").getResponse();

            Assertions.assertEquals(404, connectionResponse.getStatusCode());

        }



    }

    @Nested
    @DisplayName("GET Array of comment DTOs Tests")
    class GETArrayOfCommentsTests {


        @Test
        @DisplayName("Test getting comments by user name")
        void getCommentsByName() {

            Mockito.when(mockedRepo.findCommentByNameContaining("one")).thenReturn(mockRepoResultForOne);

            ConnectionResponse connectionResponse = connectionManager.slash("name/one").getResponse();
            CommentDTO[] dtoResponse = connectionResponse.getBodyAsArrayOf(CommentDTO.class);

            Assertions.assertArrayEquals(expectedCommentsDTOArray, dtoResponse);
        }

        @Test
        @DisplayName("Test Correct Response on GET comment by DATE")
        void getCommentsByDateYear() {
            ConnectionResponse connectionResponse = connectionManager.slash("date/1974-05-03").getResponse();
            CommentDTO[] dto = connectionResponse.getBodyAsArrayOf(CommentDTO.class);

            // String sample date value = "Fri May 03 01:58:19 BST 1974";

            for (CommentDTO commentDto: dto) {
                Assertions.assertTrue(commentDto.getDate().contains("1974"));
                Assertions.assertTrue(commentDto.getDate().contains("May"));
                Assertions.assertTrue(commentDto.getDate().contains("03"));
            }
        }

        @Test
        @DisplayName("Test GET comment by movie ID")
        void getCommentsByMovie() {

            Mockito.when(mockedRepo.findCommentByMovieId("573a1391f29313caabcd80db1")).thenReturn(mockRepoResultForOne);


            ConnectionResponse connectionResponse = connectionManager.slash("movie/573a1391f29313caabcd80db1").getResponse();
            CommentDTO[] dtoResponse = connectionResponse.getBodyAsArrayOf(CommentDTO.class);

            Assertions.assertArrayEquals(expectedCommentsDTOArray, dtoResponse);
        }

        @Test
        @DisplayName("Test GET comments by email")
        void getCommentsByEmail() {

            Mockito.when(mockedRepo.findCommentByEmail("email1@email.com")).thenReturn(mockRepoResultForOne);

            ConnectionResponse connectionResponse = connectionManager.slash("email/email1@email.com").getResponse();
            CommentDTO[] dtoResponse = connectionResponse.getBodyAsArrayOf(CommentDTO.class);

            Assertions.assertArrayEquals(expectedCommentsDTOArray, dtoResponse);
        }

        @Test
        @DisplayName("Test GET comments by Text Containing")
        void getCommentsByText() {

            Mockito.when(mockedRepo.findCommentByTextContaining("1")).thenReturn(mockRepoResultForOne);

            ConnectionResponse connectionResponse = connectionManager.slash("text/1").getResponse();
            CommentDTO[] dtoResponse = connectionResponse.getBodyAsArrayOf(CommentDTO.class);

            Assertions.assertArrayEquals(expectedCommentsDTOArray, dtoResponse);
        }

    }


    @Nested
    @DisplayName("POST Single Comment Tests")
    class POSTSingleCommentTests {


        @Test
        @DisplayName("Test Posting a Single Comment with Valid Body")
        void testPostingAComment() {

            Mockito.when(mockedRepo.save(Mockito.any())).thenReturn(comment1);

            ConnectionResponse connectionResponse = connectionManager.usingMethod("POST").withBody(comment1).getResponse();

            Assertions.assertEquals(200, connectionResponse.getStatusCode());

        }

        @Test
        @DisplayName("Test Posting a Single Comment with Invalid Body")
        void testPostingACommentWithInvalidBody() {

            Comment badComment = new Comment(null, null, "fgf", "fgfg", null);

            ConnectionResponse connectionResponse = connectionManager.usingMethod("POST").withBody(badComment).getResponse();

            Assertions.assertEquals(400, connectionResponse.getStatusCode());

        }


    }

    @Nested
    @DisplayName("PUT Comment Tests")
    class PUTCommentTests {

        @Test
        @DisplayName("Test UPDATING a Single Comment with Valid Body")
        void testPostingAComment() {

            Comment testingComment = new Comment("id1", "user one", "email1@email.com", "id", "THIS IS UPDATED COMMENT");
            Mockito.when(mockedRepo.save(Mockito.any())).thenReturn(testingComment);
            Mockito.when(mockedRepo.existsById("id1")).thenReturn(true);

            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").usingMethod("PUT").withBody("THIS IS UPDATED COMMENT").getResponse();
            CommentDTO dtoResponse = connectionResponse.getBodyAs(CommentDTO.class);

            Assertions.assertEquals(testingComment.getText(), dtoResponse.getText());

        }

        @Test
        @DisplayName("Test UPDATING a Single Comment with Empty Body")
        void testPostingACommentWithInvalidBody() {

            Mockito.when(mockedRepo.existsById("id1")).thenReturn(true);

            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").usingMethod("PUT").withBody("").getResponse();

            Assertions.assertEquals(400, connectionResponse.getStatusCode());

        }

        @Test
        @DisplayName("Test UPDATING a Single Comment with INVALID ID")
        void testPostingACommentWithInvalidID() {

            Mockito.when(mockedRepo.existsById("id1")).thenReturn(false);

            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").usingMethod("PUT").withBody("THIS IS UPDATED COMMENT").getResponse();

            Assertions.assertEquals(404, connectionResponse.getStatusCode());

        }


    }

    @Nested
    @DisplayName("DELETE Single Comment Tests")
    class DELETECommentTests {

        @Test
        @DisplayName("Test DELETING a Single Comment with VALID ID path")
        void testDeletingAComment() {
            Mockito.when(mockedRepo.existsById("id1")).thenReturn(true);
            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").usingMethod("DELETE").getResponse();
            Assertions.assertEquals(200, connectionResponse.getStatusCode());
        }

        @Test
        @DisplayName("Test DELETING a Single Comment with INVALID ID path")
        void testDeletingACommentWithInvalidID() {
            Mockito.when(mockedRepo.existsById("id1")).thenReturn(false);
            ConnectionResponse connectionResponse = connectionManager.slash("id/id1").usingMethod("DELETE").getResponse();
            Assertions.assertEquals(404, connectionResponse.getStatusCode());
        }

    }

    @Nested
    @DisplayName("DELETE Multiple Comment Tests")
    class DELETEMultipleCommentsTest {

        @Test
        @DisplayName("Test DELETING ALL COMMENTS with VALID email")
        void testDeletingCommentsByEmail() {

            Mockito.when(mockedRepo.existsById("id1")).thenReturn(true);
            ConnectionResponse connectionResponse = connectionManager.slash("all/email/email1@email.com").usingMethod("DELETE").getResponse();
            Assertions.assertEquals(200, connectionResponse.getStatusCode());
        }

        @Test
        @DisplayName("Test DELETING ALL COMMENTS with INVALID email")
        void testDeletingCommentsByINVALIDEmail() {
            Mockito.when(userMockedRepo.existsUserByEmail("email1@email.com")).thenReturn(true);
            ConnectionResponse connectionResponse = connectionManager.slash("all/email/email1@email.com").usingMethod("DELETE").getResponse();
            Assertions.assertEquals(200, connectionResponse.getStatusCode());
        }

        @Test
        @DisplayName("Test DELETING a Single Comment with INVALID ID path")
        void testDeletingACommentWithInvalidID() {
            Mockito.when(userMockedRepo.existsUserByEmail("email1@email.com")).thenReturn(false);
            ConnectionResponse connectionResponse = connectionManager.slash("all/email/email1@email.com").usingMethod("DELETE").getResponse();
            Assertions.assertEquals(404, connectionResponse.getStatusCode());
        }

    }

    @AfterAll
    static void closeup() {
        MockMFlixApplication.resetRepositoryMocks();
    }
}
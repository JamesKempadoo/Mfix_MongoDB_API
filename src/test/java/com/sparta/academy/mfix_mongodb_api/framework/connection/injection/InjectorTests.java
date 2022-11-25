package com.sparta.academy.mfix_mongodb_api.framework.framework_test.injection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.dto.comments.CommentDTO;
import com.sparta.academy.mfix_mongodb_api.framework.exception.InjectorException;
import com.sparta.academy.mfix_mongodb_api.framework.injector.Injector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class InjectorTests {

    @Mock(serializable = true)
    private static final CommentDTO mockDTO = Mockito.mock(CommentDTO.class);

    @ParameterizedTest
    @MethodSource("generateValidJSON")
    @DisplayName("Test that the getDTO method returns the given DTO")
    void testGetDTO(String validJSON) {
        assertThat(Injector.getDTO(validJSON, mockDTO.getClass()), instanceOf(mockDTO.getClass()));
    }

    @Test
    @DisplayName("Test that an exception is thrown when invalid JSON is passed")
    void testGetDTOException() {
        assertThrows(InjectorException.class, () -> Injector.getDTO("", mockDTO.getClass()));
    }

    private static Stream<String> generateValidJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return Stream.of(mapper.writeValueAsString(mockDTO));
    }
}
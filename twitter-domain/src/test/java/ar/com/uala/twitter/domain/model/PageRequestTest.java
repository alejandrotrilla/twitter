package ar.com.uala.twitter.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PageRequestTest {
    @ParameterizedTest
    @CsvSource(
            {
                    "10,0",
                    "10,1",
                    "10,10",
                    "1,0",
                    "1,1",
                    "1,10",
                    "10,0",
                    "10,1",
                    "10,10",
            }
    )
    void givenValidParametersWhenConstructorIsCalledThenReturnNewInstance(
            final int pageSize,
            final int pageIndex
    ) {
        final PageRequest result = new PageRequest(
                pageSize,
                pageIndex
        );

        assertNotNull(result);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "0,0",
                    "0,1",
                    "0,10",
                    "-10,0",
                    "-10,1",
                    "-10,10",
                    "1,-1",
                    "1,-10",
                    "10,-1",
                    "10,-10",
                    "-1,-1",
                    "-10,-10",
            }
    )
    void givenInvalidParametersWhenConstructorIsCalledThenThrowIllegalArgumentException(
            final int pageSize,
            final int pageIndex
    ) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PageRequest(
                        pageSize,
                        pageIndex
                )
        );
    }
}
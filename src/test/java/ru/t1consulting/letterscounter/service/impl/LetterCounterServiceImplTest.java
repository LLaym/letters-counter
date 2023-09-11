package ru.t1consulting.letterscounter.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.t1consulting.letterscounter.model.TextRequest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LetterCounterServiceImplTest {
    @InjectMocks
    private LetterCounterServiceImpl letterCounterService;

    @Test
    void countLetters_whenInvoke_thenMapResultReturned() {
        TextRequest request = new TextRequest("аааббббвв");
        Map<Character, Integer> expectedResult = Map.of('б', 4, 'а', 3, 'в', 2);

        Map<Character, Integer> result = letterCounterService.countLetters(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
    }

    @Test
    void countLetters_whenTextWithNumbers_thenMapResultReturned() {
        TextRequest request = new TextRequest("ааа111111ббббвв");
        Map<Character, Integer> expectedResult = Map.of('1', 6, 'б', 4, 'а', 3, 'в', 2);

        Map<Character, Integer> result = letterCounterService.countLetters(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
    }

    @Test
    void countLetters_whenTextWithSpaces_thenMapResultWithoutSpacesReturned() {
        TextRequest request = new TextRequest("ааа бббб вв");
        Map<Character, Integer> expectedResult = Map.of('б', 4, 'а', 3, 'в', 2);

        Map<Character, Integer> result = letterCounterService.countLetters(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
    }

    @Test
    void countLetters_whenTextWithOneLetterTypeOnly_thenMapResultReturned() {
        TextRequest request = new TextRequest("ааа");
        Map<Character, Integer> expectedResult = Map.of('а', 3);

        Map<Character, Integer> result = letterCounterService.countLetters(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
    }

    @Test
    void countLetters_whenTextEmpty_thenEmptyMapReturned() {
        TextRequest request = new TextRequest("");
        Map<Character, Integer> expectedResult = Map.of();

        Map<Character, Integer> result = letterCounterService.countLetters(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
    }
}
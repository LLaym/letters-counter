package ru.t1consulting.letterscounter.service;

import ru.t1consulting.letterscounter.model.TextRequest;

import java.util.Map;

public interface LetterCounterService {
    Map<Character, Integer> countLetters(TextRequest textRequest);
}

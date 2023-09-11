package ru.t1consulting.letterscounter.service.impl;

import org.springframework.stereotype.Service;
import ru.t1consulting.letterscounter.model.TextRequest;
import ru.t1consulting.letterscounter.service.LetterCounterService;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LetterCounterServiceImpl implements LetterCounterService {

    /**
     * Метод считает и возвращает количество вхождений каждого символа в тексте.
     *
     * @param textRequest запрос от пользователя, содержащий текст для проверки
     * @return коллекция Map, содержащая количество вхождений каждого символа в тексте по убыванию их количества
     */
    @Override
    public Map<Character, Integer> countLetters(TextRequest textRequest) {
        String text = textRequest.getText();

        Map<Character, Integer> characterCountMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar != ' ') {
                characterCountMap.put(currentChar, characterCountMap.getOrDefault(currentChar, 0) + 1);
            }
        }

        return characterCountMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
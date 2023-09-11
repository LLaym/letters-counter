package ru.t1consulting.letterscounter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.t1consulting.letterscounter.model.TextRequest;
import ru.t1consulting.letterscounter.service.LetterCounterService;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LetterCounterController {
    private final LetterCounterService service;

    @PostMapping("/api/countLetters")
    public Map<Character, Integer> countLetters(@RequestBody @Valid TextRequest textRequest) {
        log.info("Received POST request with body: {}", textRequest);
        return service.countLetters(textRequest);
    }
}

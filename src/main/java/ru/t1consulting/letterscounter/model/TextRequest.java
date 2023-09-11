package ru.t1consulting.letterscounter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Класс, представляющий собой входящий запрос с текстом, в котором нужно посчитать количество вхождений символов.
 *
 * @author Romanov Vitaly <vornysun@yandex.ru>
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextRequest {
    @NotBlank(message = "Текст не может быть пустым")
    private String text;
}
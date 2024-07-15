package com.javarush.krynitsyna.demoquest.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private String text;
    private List<Long> answers;
    @Getter
    private Long rightAnswer;
}

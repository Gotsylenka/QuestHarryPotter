package com.javarush.krynitsyna.demoquest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatistic {
    private long userId;
    private int playedGames;
}

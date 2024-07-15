package com.javarush.krynitsyna.demoquest.repository;

import com.javarush.krynitsyna.demoquest.entity.UserStatistic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StatisticRepository {
    private final Map<Long, UserStatistic> userStatisticMap;

    public StatisticRepository(){
        this.userStatisticMap = new HashMap<>();
    }

    public Map<Long, UserStatistic> getAll(){
        return userStatisticMap;
    }
    public Optional<UserStatistic> get(Long userId){
        return Optional.ofNullable(userStatisticMap.get(userId));
    }
    public void addUserStatistic(Long userId, UserStatistic userStatistic){
        if(!userStatisticMap.containsKey(userId)){
            userStatisticMap.put(userId,userStatistic);
        }
    }
    public void updateUserStatistic(Long userId) {
        UserStatistic userStatistic = userStatisticMap.get(userId);
        if(userStatistic != null) {
            userStatistic.setPlayedGames(userStatistic.getPlayedGames() + 1);
        }
    }
}

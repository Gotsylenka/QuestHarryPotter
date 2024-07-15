package com.javarush.krynitsyna.demoquest.repository;

import com.javarush.krynitsyna.demoquest.entity.Question;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestionRepository implements Repository<Question> {

    private final Map<Long,Question> questionsMap;
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    public QuestionRepository(){
        questionsMap = new HashMap<>();

        questionsMap.put(1L, new Question(1L, "С помощью какого заклинания ты сможешь победить могучего тролля в ванной комнате школы Хогвартс?", Arrays.asList(1L, 2L),1L));
        questionsMap.put(2L,new Question(2L, "Кто был директором Хогвартса до Альбуса Дамблдора?",Arrays.asList(3l,4L),4L));
        questionsMap.put(3L,new Question(3L,"Какой ингредиент НЕ используется для приготовления оборотного зелья?",Arrays.asList(5L,6L,7L,8L),7L));
        questionsMap.put(4L, new Question(4L, "Какой цвет у крови дракона по версии Альбуса Дамблдора?", Arrays.asList(9L,10L),9L));
        questionsMap.put(5L, new Question(5L, "Какой артефакт был разрушен в Турнире Трех Волшебников?",Arrays.asList(11L,12L,13L),12L));
        questionsMap.put(6L,new Question(6L,"Какое место использовал Гарри для хранения своей копии учебника \"Зелья и настойки\" с пометками Полукровного Принца?",Arrays.asList(14L,15L,16L,17L),14L));
        questionsMap.put(7L,new Question(7L,"Какой предмет, считавшийся потерянным, Гермиона нашла в зале запретных книг?",Arrays.asList(18L,19L,20L),19L));

    }
    public Optional<Long> getRightAnswerById(Long questionId){
        return get(questionId).map(Question::getRightAnswer);

    }
    @Override
    public Collection<Question> getAll() {
        return questionsMap.values();
    }

    @Override
    public Optional<Question> get(Long id) {
        return Optional.ofNullable(questionsMap.get(id));
    }

    @Override
    public void create(Question entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Question entity) {
        questionsMap.put(entity.getId(),entity);
    }

    @Override
    public void delete(Question entity) {
        questionsMap.remove(entity.getId());
    }
}

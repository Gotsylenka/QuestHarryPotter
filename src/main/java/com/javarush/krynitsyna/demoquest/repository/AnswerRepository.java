package com.javarush.krynitsyna.demoquest.repository;

import com.javarush.krynitsyna.demoquest.entity.Answer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AnswerRepository implements Repository<Answer> {
    private final Map<Long,Answer> answersMap;
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    public AnswerRepository(){
        answersMap = new HashMap<>();

        answersMap.put(1L, new Answer(1L, "Wingardium Leviosa"));
        answersMap.put(2L, new Answer(2L,"Avada Kedavra"));
        answersMap.put(3L, new Answer(3L,"Брайан Грейшот"));
        answersMap.put(4L, new Answer(4L,"Армандо Диппет"));
        answersMap.put(5L, new Answer(5l,"Шкурка бумсланга"));
        answersMap.put(6L, new Answer(6L,"Колючка дикого чабреца"));
        answersMap.put(7L, new Answer(7L, "Шелуха муравьиного молочая"));
        answersMap.put(8L, new Answer(8L, "Лепестки травы пижмы"));
        answersMap.put(9L, new Answer(9L, "Зеленый"));
        answersMap.put(10L, new Answer(10L, "Золотой"));
        answersMap.put(11L, new Answer(11L,"Кубок Огня"));
        answersMap.put(12L, new Answer(12L,"Кубок Хогвартса"));
        answersMap.put(13L, new Answer(13L,"Мантия-невидимка"));
        answersMap.put(14L, new Answer(14L,"Комната по запросу"));
        answersMap.put(15L, new Answer(15L,"Библиотека Хогвартса"));
        answersMap.put(16L, new Answer(16L,"В кабинете зельеварения"));
        answersMap.put(17L, new Answer(17L,"У себя в комнате"));
        answersMap.put(18L, new Answer(18L,"Книгу Роуэны Равенкло"));
        answersMap.put(19L, new Answer(19L,"Журнал Тома Риддла"));
        answersMap.put(20L, new Answer(20L,"Волшебный мундштук Годрика Гриффиндора"));
    }

    public String rightAnswerById(Long id){
        return answersMap.get(id).getText();
    }
    @Override
    public Collection<Answer> getAll() {
        return answersMap.values();
    }

    @Override
    public Optional<Answer> get(Long id) {
        return Optional.ofNullable(answersMap.get(id));
    }

    @Override
    public void create(Answer entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Answer entity) {
        answersMap.put(entity.getId(),entity);
    }

    @Override
    public void delete(Answer entity) {
        answersMap.remove(entity.getId());
    }
}

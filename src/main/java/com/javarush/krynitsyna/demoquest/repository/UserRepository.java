package com.javarush.krynitsyna.demoquest.repository;

import com.javarush.krynitsyna.demoquest.entity.Role;
import com.javarush.krynitsyna.demoquest.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {
    private final Map<Long, User> userMap = new HashMap<>();
    public  static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public UserRepository(){
        userMap.put(1L,new User(1L, "Darya","1111", Role.ADMIN));
        userMap.put(2L, new User(2L,"Nikita","nik4447", Role.USER));
        userMap.put(3L, new User(3L, "Nastya", "nekytzyn", Role.GUEST));
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public void create(User entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(User entity) {
        userMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(User entity) {
        userMap.remove(entity.getId());
    }

    public Optional<User> getByUsername(String username){
        return userMap.values().stream()
                .filter(user -> user.getLogin().equals(username))
                .findFirst();
    }
}

package workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import workshop.entity.UserEntity;
import workshop.model.User;
import workshop.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Async
    public CompletableFuture<List<User>> save(MultipartFile file) {
        log.info("save() method called");
        long startTime = System.currentTimeMillis();
        List<User> users = parseCsvFile(file);
        List<UserEntity> entities = users.stream().map(this::convert).collect(Collectors.toList());
        log.info("saving users, size is {}", entities.size() + ", thread name: " + Thread.currentThread().getName());
        entities = repository.saveAll(entities);
        long endTime = System.currentTimeMillis();
        users = entities.stream().map(this::convert).collect(Collectors.toList());
        log.info("total time taken: " + (endTime - startTime));
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<List<User>> getAllUsers() {
        log.info("getAllUsers() method called");
        log.info("retrieve all users by thread: {}", Thread.currentThread().getName());
        List<UserEntity> entities = repository.findAll();
        List<User> users = entities.stream().map(this::convert).collect(Collectors.toList());
        return CompletableFuture.completedFuture(users);
    }

    private User convert(UserEntity entity) {
        User user = new User();
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setGender(entity.getGender());
        return user;
    }

    private UserEntity convert(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setGender(user.getGender());
        return entity;
    }

    public List<User> parseCsvFile(MultipartFile file) {
        log.info("read() method called");
        List<User> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                User user = new User();
                user.setName(arr[0]);
                user.setEmail(arr[1]);
                user.setGender(arr[2]);
                result.add(user);
            }
        } catch (IOException e) {
            log.error("Exception occurred while reading file {}", e);
        }
        return result;
    }
}

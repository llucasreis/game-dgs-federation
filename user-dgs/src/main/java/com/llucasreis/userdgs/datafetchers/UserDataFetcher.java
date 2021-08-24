package com.llucasreis.userdgs.datafetchers;

import com.llucasreis.userdgs.domain.entities.User;
import com.llucasreis.userdgs.repositories.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DgsComponent
public class UserDataFetcher {

    private final UserRepository userRepository;

    @Autowired
    public UserDataFetcher(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsQuery
    public List<User> users() {
        return this.userRepository.findAll();
    }

    @DgsEntityFetcher(name = "User")
    public User user(Map<String, Object> values) {
        Long id = Long.parseLong((String) values.get("id"));

        return this.userRepository.findById(id).orElse(null);
    }
}

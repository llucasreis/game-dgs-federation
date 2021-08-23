package com.llucasreis.userdgs.datafetchers;

import com.llucasreis.userdgs.domain.entities.User;
import com.llucasreis.userdgs.repositories.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}

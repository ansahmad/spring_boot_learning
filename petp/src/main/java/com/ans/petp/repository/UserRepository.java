package com.ans.petp.repository;

import com.ans.petp.entity.JournalEntry;
import com.ans.petp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    User deleteByUsername(String username);
}

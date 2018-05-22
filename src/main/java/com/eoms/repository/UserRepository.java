package com.eoms.repository;

import com.eoms.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    User findByNameAndPassword(String name,String password);
}

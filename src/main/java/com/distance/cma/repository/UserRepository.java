package com.distance.cma.repository;

import com.distance.cma.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
}

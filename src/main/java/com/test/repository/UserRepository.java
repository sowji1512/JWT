package com.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	 Users findByUserName(String userName);
     Boolean existsByUserName(String userName);
     Boolean existsByUserEmail(String userEmail);
}

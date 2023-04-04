package com.ab.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ab.entities.Order;
import com.ab.entities.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
	User findByUsername(String username);
	
	
}

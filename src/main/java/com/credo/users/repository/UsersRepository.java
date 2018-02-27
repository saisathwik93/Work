package com.credo.users.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credo.users.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	@Query("SELECT r FROM Users r where r.username=:username and r.password=:password")
	public Users getUser(@Param("username") String username,@Param("password") String password);
	
	@Query("SELECT r FROM Users r where r.username=:username")
	public Users getUserByUsername(@Param("username") String username);
}
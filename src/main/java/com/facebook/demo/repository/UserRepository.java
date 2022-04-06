package com.facebook.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.facebook.demo.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

	@Query(
		value="SELECT * FROM user WHERE id=?1",
		nativeQuery=true
	)
	public User findUserById(long id) ;
	@Modifying
    @Transactional
	@Query(
			value="DELETE FROM user WHERE id=?1",
			nativeQuery=true
		)
	public void deleteUserById(long id);
	@Query(
			value="SELECT * FROM user WHERE name Like '%?1%'",
			nativeQuery=true
		)
	public List<User> contain(String id);
}

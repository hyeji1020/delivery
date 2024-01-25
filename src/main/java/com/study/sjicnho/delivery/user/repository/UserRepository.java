package com.study.sjicnho.delivery.user.repository;

import com.study.sjicnho.delivery.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Override
//    public User getById(Integer id);


}

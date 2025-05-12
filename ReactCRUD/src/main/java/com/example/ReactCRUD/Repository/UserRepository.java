package com.example.ReactCRUD.Repository;

import com.example.ReactCRUD.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}

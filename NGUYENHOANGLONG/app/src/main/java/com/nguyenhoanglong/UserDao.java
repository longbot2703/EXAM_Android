package com.nguyenhoanglong;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert (onConflict = REPLACE)
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

    @Query("SELECT * FROM user")
    List<UserEntity> getAllUser();

    @Query("SELECT * FROM user WHERE id = :id")
    UserEntity getUserById(int id);

    @Query("SELECT * FROM user WHERE username = :username")
    UserEntity getUserByName(String username);

    @Query("SELECT * FROM user WHERE gender = :gender")
    UserEntity getUserByGender(int gender);

    @Query("DELETE FROM user")
    void deleteAll();
}

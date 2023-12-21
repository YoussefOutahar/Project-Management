package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  @Query(
          value = """
                  select u from User u inner join Token t
                  on u.id = t.user.id
                  where t.token = :token
                  """
  )
  Optional<User> findByToken(String token);
}

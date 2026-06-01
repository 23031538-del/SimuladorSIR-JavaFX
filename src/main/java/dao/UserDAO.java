package dao;

import model.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User, java.util.UUID> {
    Optional<User> findByUsername(String username) throws Exception;
}

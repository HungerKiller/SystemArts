package com.system.arts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.arts.entity.Resource;
import com.system.arts.entity.User;
import com.system.arts.entity.UserFavorite;
import com.system.arts.repository.UserFavoriteRepository;

@Service
public class UserFavoriteService {

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    public List<UserFavorite> getAllUserFavorites() {
        return userFavoriteRepository.findAll();
    }

    public List<UserFavorite> getUserFavoritesByUserId(int userId) {
        return userFavoriteRepository.findByUserId(userId);
    }

    public List<UserFavorite> getUserFavoritesByResourceId(int resourceId) {
        return userFavoriteRepository.findByResourceId(resourceId);
    }

    public UserFavorite createUserFavorite(UserFavorite userFavorite) {
        return userFavoriteRepository.save(userFavorite);
    }
    
    public void deleteUserFavorite(int id) {
        UserFavorite userFavorite = userFavoriteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserFavorite not found with id " + id));
        userFavoriteRepository.delete(userFavorite);
    }
}

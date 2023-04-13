package com.system.arts.controller;

import com.system.arts.entity.UserFavorite;
import com.system.arts.service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user-favorites")
public class UserFavoriteController {

    @Autowired
    private UserFavoriteService userFavoriteService;

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<UserFavorite>> getUserFavoritesByUserId(@PathVariable int userId) {
        List<UserFavorite> userFavorites = userFavoriteService.getUserFavoritesByUserId(userId);
        return new ResponseEntity<>(userFavorites, HttpStatus.OK);
    }

    @GetMapping("/byResource/{resourceId}")
    public ResponseEntity<List<UserFavorite>> getUserFavoritesByResourceId(@PathVariable int resourceId) {
        List<UserFavorite> userFavorites = userFavoriteService.getUserFavoritesByResourceId(resourceId);
        return new ResponseEntity<>(userFavorites, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<UserFavorite> getAllUserFavorites() {
        return userFavoriteService.getAllUserFavorites();
    }

    @PostMapping("/")
    public ResponseEntity<UserFavorite> addUserFavorite(@RequestBody UserFavorite userFavorite) {
        UserFavorite createdUserFavorite = userFavoriteService.createUserFavorite(userFavorite);
        return new ResponseEntity<>(createdUserFavorite, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserFavorite(@PathVariable int id) {
        userFavoriteService.deleteUserFavorite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

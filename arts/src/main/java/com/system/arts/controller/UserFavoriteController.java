package com.system.arts.controller;

import com.system.arts.entity.UserFavorite;
import com.system.arts.service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/")
    public ResponseEntity<Void> addUserFavorite(@RequestParam int userId, @RequestParam int resourceId) {
        userFavoriteService.addUserFavorite(userId, resourceId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserFavorite(@RequestParam int id) {
        userFavoriteService.deleteUserFavorite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.system.arts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.system.arts.entity.Announcement;
import com.system.arts.entity.Comment;
import com.system.arts.entity.Resource;
import com.system.arts.entity.ResourceType;
import com.system.arts.entity.User;
import com.system.arts.entity.UserFavorite;
import com.system.arts.entity.Role;
import com.system.arts.service.AnnouncementService;
import com.system.arts.service.CommentService;
import com.system.arts.service.ResourceService;
import com.system.arts.service.ResourceTypeService;
import com.system.arts.service.UserFavoriteService;
import com.system.arts.service.UserService;

@SpringBootApplication
public class ArtsApplication implements CommandLineRunner {

	@Autowired
    private UserService userService;

	@Autowired
	private ResourceTypeService resourceTypeService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserFavoriteService userFavoriteService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AnnouncementService announcementService;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ArtsApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		if (userService.getAllUsers().size() != 0) {
			return;
		}

        // Create users
        User user1 = new User("user1", "password1", "email1", 20, Role.ADMIN);
        User user2 = new User("user2", "password2", "email2", 20, Role.USER);
		User user3 = new User("user3", "password3", "email3", 20, Role.USER);
		User user4 = new User("user4", "password4", "email4", 20, Role.USER);
		User user5 = new User("user5", "password5", "email5", 20, Role.USER);
		userService.createUser(user1);
		userService.createUser(user2);
		userService.createUser(user3);
		userService.createUser(user4);
		userService.createUser(user5);

		// Create resource types
		ResourceType resourceType1 = new ResourceType("ResourceType1", "description1");
		ResourceType resourceType2 = new ResourceType("ResourceType2", "description2");
		ResourceType resourceType3 = new ResourceType("ResourceType3", "description3");
		resourceTypeService.createResourceType(resourceType1);
		resourceTypeService.createResourceType(resourceType2);
		resourceTypeService.createResourceType(resourceType3);

		// Create resources
		Resource resource1 = new Resource("Resource1", "description1", 100, 11, user1, resourceType1);
		Resource resource2 = new Resource("Resource2", "description2", 110, 12.5, user1, resourceType2);
		Resource resource3 = new Resource("Resource3", "description3", 200, 13, user2, resourceType1);
		Resource resource4 = new Resource("Resource4", "description4", 10, 14, user2, resourceType3);
		Resource resource5 = new Resource("Resource5", "description5", 50, 15, user3, resourceType1);
		Resource resource6 = new Resource("Resource6", "description6", 5, 16, user3, resourceType3);
		resourceService.createResource(resource1);
		resourceService.createResource(resource2);
		resourceService.createResource(resource3);
		resourceService.createResource(resource4);
		resourceService.createResource(resource5);
		resourceService.createResource(resource6);

		// Create user favorites
		userFavoriteService.createUserFavorite(new UserFavorite(user1, resource5));
		userFavoriteService.createUserFavorite(new UserFavorite(user1, resource6));
		userFavoriteService.createUserFavorite(new UserFavorite(user2, resource5));
		userFavoriteService.createUserFavorite(new UserFavorite(user2, resource6));
		userFavoriteService.createUserFavorite(new UserFavorite(user3, resource1));
		userFavoriteService.createUserFavorite(new UserFavorite(user3, resource2));
		userFavoriteService.createUserFavorite(new UserFavorite(user3, resource3));
		userFavoriteService.createUserFavorite(new UserFavorite(user3, resource4));
		userFavoriteService.createUserFavorite(new UserFavorite(user3, resource5));
		userFavoriteService.createUserFavorite(new UserFavorite(user3, resource6));
		userFavoriteService.createUserFavorite(new UserFavorite(user4, resource5));
		userFavoriteService.createUserFavorite(new UserFavorite(user4, resource6));

		// Create comments
		Comment comment1 = new Comment("Comment1", user1, resource1);
		Comment comment2 = new Comment("Comment2", user2, resource1);
		Comment comment3 = new Comment("Comment3", user3, resource1);
		Comment comment4 = new Comment("Comment4", user4, resource1);
		Comment comment5 = new Comment("Comment5", user5, resource1);
		Comment comment6 = new Comment("Comment1", user1, resource2);
		Comment comment7 = new Comment("Comment1", user1, resource3);
		commentService.createComment(comment1);
		commentService.createComment(comment2);
		commentService.createComment(comment3);
		commentService.createComment(comment4);
		commentService.createComment(comment5);
		commentService.createComment(comment6);
		commentService.createComment(comment7);

		// Create announcements
		Announcement announcement1 = new Announcement("Title1", "Content1", true);
		Announcement announcement2 = new Announcement("Title2", "Content2", true);
		Announcement announcement3 = new Announcement("Title3", "Content3", true);
		Announcement announcement4 = new Announcement("Title4", "Content4", true);
		announcementService.createAnnouncement(announcement1);
		announcementService.createAnnouncement(announcement2);
		announcementService.createAnnouncement(announcement3);
		announcementService.createAnnouncement(announcement4);
    }
}

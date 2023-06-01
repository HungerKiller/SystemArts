package com.system.arts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.system.arts.entity.Announcement;
import com.system.arts.entity.Comment;
import com.system.arts.entity.Order;
import com.system.arts.entity.OrderProduct;
import com.system.arts.entity.OrderStatus;
import com.system.arts.entity.Resource;
import com.system.arts.entity.ResourceFile;
import com.system.arts.entity.ResourceType;
import com.system.arts.entity.User;
import com.system.arts.entity.UserFavorite;
import com.system.arts.entity.Role;
import com.system.arts.service.AnnouncementService;
import com.system.arts.service.CommentService;
import com.system.arts.service.OrderProductService;
import com.system.arts.service.OrderService;
import com.system.arts.service.ResourceFileService;
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
	private ResourceFileService resourceFileService;

	@Autowired
	private UserFavoriteService userFavoriteService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderProductService orderProductService;

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
        User user2 = new User("Zhang", "password2", "email2", 20, Role.USER);
		User user3 = new User("Li", "password3", "email3", 20, Role.USER);
		User user4 = new User("Wang", "password4", "email4", 20, Role.USER);
		userService.createUser(user1);
		userService.createUser(user2);
		userService.createUser(user3);
		userService.createUser(user4);

		// Create resource types
		ResourceType resourceType1 = new ResourceType("人物画", "人物画是绘画的一种，以人物形象为主体的绘画之通称。");
		ResourceType resourceType2 = new ResourceType("院体画", "院体画，简称院画，是中国传统绘画的一种，狭义上是指中国古代皇室宫廷画家的绘画作品，广义上则包括宫廷绘画在内和受到宫廷绘画影响的中国传统绘画的一个类别，以及倾向于中国古代宫廷绘画的这种画风。院体画风格多以工整细腻、细节繁复而写实逼真为主。");
		ResourceType resourceType3 = new ResourceType("水墨画", "水墨画是由水和墨调配成不同深浅的墨色所画出的画，是绘画的一种形式，更多时候，水墨画被视为中国传统绘画，也就是国画的代表。");
		resourceTypeService.createResourceType(resourceType1);
		resourceTypeService.createResourceType(resourceType2);
		resourceTypeService.createResourceType(resourceType3);

		// Create resources
		Resource resource1 = new Resource("清明上河图", "五代两宋是中国人物画深入发展的时期，随着宫廷画院的兴办，工笔重着色人物画更趋精美，又随着文人画的兴起，民间稿本被李公麟提高为一种被称为白描的绘画样式。宋代城乡经济的发展，宋与金的斗争，社会风俗画和具有现实意义的历史故事画亦蓬勃发展。张择端的杰作《清明上河图》便产生于这一时期。", 100, 25, user2, resourceType1);
		Resource resource2 = new Resource("虢国夫人游春图", "《虢国夫人游春图》重人物内心刻画，通过劲细的线描和色调的敷设，浓艳而不失其秀雅，精工而不板滞。全画构图疏密有致，错落自然。人与马的动势舒缓从容，正应游春主题。画家不著背景，只以湿笔点出斑斑草色以突出人物，意境空潆清新。图中用线纤细，圆润秀劲，在劲力中透著妩媚。设色典雅富丽，具装饰意味，格调活泼明快。画面上洋溢著雍容、自信、乐观的盛唐风貌。", 110, 23, user2, resourceType2);
		Resource resource3 = new Resource("水墨画", "水墨画是由水和墨调配成不同深浅的墨色所画出的画，是绘画的一种形式，更多时候，水墨画被视为中国传统绘画，也就是国画的代表。", 200, 13, user2, resourceType3);
		Resource resource4 = new Resource("风景画", "墨水是国画的起源，以笔墨运用的技法基础画成墨水画。", 10, 12, user2, resourceType3);
		Resource resource5 = new Resource("人物画", "李伦将水墨的精髓运用到书法当中，在中国书协展览部主任白煦等人力推的水墨书法的基础上，开创了新的技法，创作出大量水墨书法作品。", 50, 14, user3, resourceType1);
		Resource resource6 = new Resource("古时美女图", "笔法或描法一方面服从于形象的结构质感、量感与神情，另方面也要传达作者的感情，同时还用以体现作者的个人风格。在写意人物画中，笔墨相互为用，笔中有墨，墨中有笔，一笔落纸，既要状物传神，又要抒情达意，还要显现个人风格，其难易程度远胜于山水花鸟画。", 5, 17, user1, resourceType1);
		Resource resource7 = new Resource("李太白吟行图", "此图为李梁楷所作。", 1, 16, user3, resourceType1);
		Resource resource8 = new Resource("簪花仕女图", "《簪花仕女图》是周昉贵族人物画风格的代表。同时也体现出贵族仕女养尊处优、无所事事、游戏于花蝶鹤犬之间的生活情态。", 1, 20, user3, resourceType2);
		Resource resource9 = new Resource("仕女图", "安史之乱以后，当代统治阶级为了粉饰太平，提倡所谓“文治”，也正好吻合了当时人民历经战乱、渴望安宁社会的生活的心情，宴游的风气从此大开，奢侈之风成为天宝以后统治者崇尚的对象，到了贞元年间，这种风气就更为突出。杜牧当时这样描述：至于贞元末，风流悠绮靡。周昉的《簪花仕女图》正是这个时期的典型代表，画家如实的描绘了在奢靡风气支配下的唐代宫廷仕女嬉游生活的典型环境。", 1, 24, user3, resourceType2);
		Resource resource10 = new Resource("韩熙载夜宴图", "《韩熙载夜宴图》是五代十国时期南唐画家顾闳中的绘画作品，现存宋摹本，绢本设色，现藏于北京故宫博物院。 《韩熙载夜宴图》描绘了官员韩熙载家设夜宴载歌行乐的场面。此画绘写的就是一次完整的韩府夜宴过程，即琵琶演奏、观舞、宴间休息、清吹、欢送宾客五段场景。整幅作品线条遒劲流畅，工整精细，构图富有想象力。", 1, 20, user3, resourceType1);
		resourceService.createResource(resource1);
		resourceService.createResource(resource2);
		resourceService.createResource(resource3);
		resourceService.createResource(resource4);
		resourceService.createResource(resource5);
		resourceService.createResource(resource6);
		resourceService.createResource(resource7);
		resourceService.createResource(resource8);
		resourceService.createResource(resource9);
		resourceService.createResource(resource10);

		// Resource file
		ResourceFile resourceFile1 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\3__1.jpg", "3__1.jpg", 3);
		ResourceFile resourceFile2 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\4__2.jpg", "4__2.jpg", 4);
		ResourceFile resourceFile3 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\6__4.jpg", "6__4.jpg", 6);
		ResourceFile resourceFile4 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\5__3.jpg", "5__3.jpg", 5);
		ResourceFile resourceFile5 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\6__5.jpg", "6__5.jpg", 6);
		ResourceFile resourceFile6 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\8__9.jpg", "8__9.jpg", 8);
		ResourceFile resourceFile7 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\8__10.jpg", "8__10.jpg",8);
		ResourceFile resourceFile8 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\9__10.jpg", "9__10.jpg",9);
		ResourceFile resourceFile9 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\1__11.jpg", "1__11.jpg",1);
		ResourceFile resourceFile10 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\2__12.jpg", "2__12.jpg",2);
		ResourceFile resourceFile11 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\7__5.jpg", "7__5.jpg", 7);
		ResourceFile resourceFile12 = new ResourceFile(".\\arts\\src\\main\\resources\\static\\10__13.jpg", "10__13.jpg",10);
		resourceFileService.createResourceFile(resourceFile1);
		resourceFileService.createResourceFile(resourceFile2);
		resourceFileService.createResourceFile(resourceFile3);
		resourceFileService.createResourceFile(resourceFile4);
		resourceFileService.createResourceFile(resourceFile5);
		resourceFileService.createResourceFile(resourceFile6);
		resourceFileService.createResourceFile(resourceFile7);
		resourceFileService.createResourceFile(resourceFile8);
		resourceFileService.createResourceFile(resourceFile9);
		resourceFileService.createResourceFile(resourceFile10);
		resourceFileService.createResourceFile(resourceFile11);
		resourceFileService.createResourceFile(resourceFile12);

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
		Comment comment1 = new Comment("谢谢分享！", user1, resource1.getId());
		Comment comment2 = new Comment("我最喜欢《清明上河图》了。", user2, resource1.getId());
		Comment comment3 = new Comment("张择端太厉害了，细节画的真好。", user3, resource1.getId());
		Comment comment4 = new Comment("展示了宋代的民生。", user4, resource1.getId());
		Comment comment5 = new Comment("《游春图》记述了显赫一时的杨氏三姊妹在众女仆和从监的引导和护卫下，一行八骑九人（包括一小女孩）挥鞭策马，外出踏青游春的情景。图中人物神态从容，乘骑步伐轻松自如，人物服饰轻薄鲜明。", user2, resource2.getId());
		Comment comment6 = new Comment("点赞！点赞！", user1, resource2.getId());
		commentService.createComment(comment1);
		commentService.createComment(comment2);
		commentService.createComment(comment3);
		commentService.createComment(comment4);
		commentService.createComment(comment5);
		commentService.createComment(comment6);

		// Create announcements
		Announcement announcement1 = new Announcement("创意绘画活动：发现你的艺术天赋", "尊敬的校园成员，\n"
		+"我们非常高兴地宣布即将举办一场精彩的校园艺术绘画活动！这将是一个充满创意和艺术的机会，让你发现和展示你的艺术天赋。无论你是有经验的绘画爱好者，还是对艺术充满好奇的新手，都欢迎加入我们的活动，与我们一起探索绘画的魅力。"
		+"\n活动详情如下：\n日期：2023年6月10日\n时间：15:00\n地点：艺术中心", true);
		Announcement announcement2 = new Announcement("校园艺术绘画大赛：展示你的艺术风采", "尊敬的校园社群成员，\n"
		+"我们非常激动地宣布，校园艺术绘画大赛即将拉开帷幕！这是一个展示你的艺术风采、挑战自我并赢得荣誉的绝佳机会。无论你是绘画爱好者、学院艺术专业的学生，还是对绘画感兴趣的校园成员，都欢迎加入我们的大赛，与我们一同创造出独特的艺术作品。", true);
		Announcement announcement3 = new Announcement("校园艺术绘画工作坊：发现创意与美的碰撞", "尊敬的校园社群成员，\n"
		+"我们诚挚邀请你参加我们即将举办的校园艺术绘画工作坊！这是一个探索创意与美的碰撞、提升绘画技巧和艺术感知力的绝佳机会。不论你是对绘画充满热情的学生，还是对艺术表达感兴趣的新手，都欢迎加入我们的工作坊，与我们一同发现艺术的魅力。", true);
		announcementService.createAnnouncement(announcement1);
		announcementService.createAnnouncement(announcement2);
		announcementService.createAnnouncement(announcement3);

		// Order
		Order order1 = new Order(user1, OrderStatus.ACTIVE);
		order1.setIsCart(true);
		Order order2 = new Order(user2, OrderStatus.COMPLETED);
		Order order3 = new Order(user2, OrderStatus.COMPLETED);
		Order order4 = new Order(user2, OrderStatus.PAYED);
		this.orderService.createOrder(order1);
		this.orderService.createOrder(order2);
		this.orderService.createOrder(order3);
		this.orderService.createOrder(order4);

		// Order products
		OrderProduct orderProduct1 = new OrderProduct(order1.getId(), resource1, 1);
		OrderProduct orderProduct2 = new OrderProduct(order1.getId(), resource2, 2);
		OrderProduct orderProduct3 = new OrderProduct(order1.getId(), resource3, 3);
		OrderProduct orderProduct4 = new OrderProduct(order2.getId(), resource2, 3);
		OrderProduct orderProduct5 = new OrderProduct(order2.getId(), resource3, 4);
		OrderProduct orderProduct6 = new OrderProduct(order3.getId(), resource1, 3);
		OrderProduct orderProduct7 = new OrderProduct(order3.getId(), resource3, 3);
		OrderProduct orderProduct8 = new OrderProduct(order4.getId(), resource1, 3);
		OrderProduct orderProduct9 = new OrderProduct(order4.getId(), resource3, 3);
		this.orderProductService.createOrderProduct(orderProduct1);
		this.orderProductService.createOrderProduct(orderProduct2);
		this.orderProductService.createOrderProduct(orderProduct3);
		this.orderProductService.createOrderProduct(orderProduct4);
		this.orderProductService.createOrderProduct(orderProduct5);
		this.orderProductService.createOrderProduct(orderProduct6);
		this.orderProductService.createOrderProduct(orderProduct7);
		this.orderProductService.createOrderProduct(orderProduct8);
		this.orderProductService.createOrderProduct(orderProduct9);
    }
}

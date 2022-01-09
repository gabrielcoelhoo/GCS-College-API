package com.gabriel.gcscollegeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcscollegeApiApplication {
//	
//	@Autowired
//	private UserServiceImpl userService;
//
//	@Autowired
//	private RoleServiceImpl roleService;


	public static void main(String[] args) {
		SpringApplication.run(GcscollegeApiApplication.class, args);
	}
//	
//	public void run(String... args) throws Exception {
//		if (roleService.findAll().isEmpty()) {
//			roleService.saveOrUpdate(new Role(ConstantUtils.ADMIN.toString()));
//			roleService.saveOrUpdate(new Role(ConstantUtils.USER.toString()));
//		}
//
//		if (userService.findAll().isEmpty()) {
//			User user1 = new User();
//			user1.setEmail("test@user.com");
//			user1.setName("Test User");
//			user1.setAddress("3 bess");
//			user1.setCountry("brasil");
//			user1.setPhoneNumber("34567890");
//			user1.setStudentComments("sasasa");
//			user1.setSurname("santos");
//			
//			user1.setRole(roleService.findByName(ConstantUtils.USER.toString()));
//			user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
//			userService.saveOrUpdate(user1);
//
//			User user2 = new User();
//			user2.setEmail("test@admin.com");
//			user2.setName("Test Admin");
//			user2.setAddress("3 bess");
//			user2.setCountry("brasil");
//			user2.setPhoneNumber("34567890");
//			user2.setStudentComments("sasasa");
//			user2.setSurname("santos");
//		
//			user2.setRole(roleService.findByName(ConstantUtils.ADMIN.toString()));
//			user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
//			userService.saveOrUpdate(user2);
//		}
//}
}
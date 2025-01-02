package ma.ensa.accountManagement_service;

import ma.ensa.accountManagement_service.entities.Admin;
import ma.ensa.accountManagement_service.entities.UserApp;
import ma.ensa.accountManagement_service.enums.Role;
import ma.ensa.accountManagement_service.repositories.AdminRepo;
import ma.ensa.accountManagement_service.repositories.UserAppRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner initializeAdmin(AdminRepo adminRepo, PasswordEncoder passwordEncoder) {
		return args -> {

			if (adminRepo.findByUsername("admin").isEmpty()) {


				Admin admin = new Admin();
				admin.setNom("admin");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setUsername("admin");
				admin.setEmail("Admin@gmail.com") ;
				admin.setPrenom("Super");
				admin.setNumTelephone("0000000000");
				admin.setRole(Role.ADMIN);


				adminRepo.save(admin);
				System.out.println("Admin par défaut créé avec succès !");
			} else {
				System.out.println("Un admin existe déjà, aucune action nécessaire.");
			}
		};
	}
}

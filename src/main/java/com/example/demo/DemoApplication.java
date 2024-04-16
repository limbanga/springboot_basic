package com.example.demo;

import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.exceptions.CustomValidationException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.RegisterRequest;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication
		implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final UserService userService;
	private final ProductService productService;
	private final OrderService orderService;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Initializing data...");
		// Init admin
		var registerRegister = RegisterRequest.builder()
				.username("admin")
				.password("password")
				.firstName("First Name")
				.lastName("Last Name")
				.build();
		userService.register(registerRegister, Role.ADMIN);
		System.out.println("Admin created" + registerRegister.getUsername());

		for (int i = 0; i < 5; i++) {
			var product = Product.builder()
					.name("Product " + i)
					.description("Description " + i)
					.price(1000.0 + i)
					.quantity(10 + i)
					.build();
			productService.create(product);
			System.out.println("Product created: " + product.getName());
		}

		for (int i = 0; i < 3; i++) {
			var	order = Order.builder()
					.product(productService.get(i + 1L))
					.quantity(1 + i)
					.owner((User)userService.loadUserByUsername("admin"))
					.build();
			orderService.create(order);
			System.out.println("Order created: " + order.getId());
		}
	}
}

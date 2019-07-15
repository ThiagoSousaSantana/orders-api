package br.com.thiagosousa.ordersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableSpringDataWebSupport
@SpringBootApplication
public class OrdersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApiApplication.class, args);
	}

}

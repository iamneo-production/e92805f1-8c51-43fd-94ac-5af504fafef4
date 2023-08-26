package service.registry.hackthon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Sreeram
 * Description: Service registry, which will on-board configured micro services
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryHackthonApplication { 
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryHackthonApplication.class, args);
	}

}

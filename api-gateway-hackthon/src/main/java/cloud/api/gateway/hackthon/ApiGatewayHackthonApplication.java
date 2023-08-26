package cloud.api.gateway.hackthon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Sreeram
 * Description: Cloud gate way to communicate registered other services.
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayHackthonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayHackthonApplication.class, args);
	}

}

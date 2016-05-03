package ie.martin;

import com.netflix.discovery.converters.Auto;
import ie.martin.service.ShareService;
import ie.martin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.List;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableAutoConfiguration
public class RestApiMicroServiceApplication implements CommandLineRunner {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	ShareService share;

	@Autowired
	UserService user;

	public static void main(String[] args) {
		SpringApplication.run(RestApiMicroServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		share.getURIs();
		user.getURIs();
	/*	List<ServiceInstance> services = discoveryClient.getInstances("sharePriceMS");
		System.out.println("------------");
		services.forEach(System.out::println);
		System.out.println(services.get(0).getPort());
		System.out.println(services.get(0).getHost());
		System.out.println(services.get(0).getServiceId());
		System.out.println(services.get(0).getUri());
		System.out.println(services.get(0).getClass());


		System.out.println("------------");*/


	}
}

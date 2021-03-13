package kr.dataportal.datahubcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DataHubCoreApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DataHubCoreApplication.class)
				.properties("" +
						"spring.config.location=" +
						"classpath:/application.yml," +
						"optional:C:/repository/_secrets/c4k-blog.yml," +
						"optional:/home/datahub/_secrets/c4k-blog.yml"
				)
				.run(args);
	}

}

package com.app;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@ServletComponentScan
public class Application {

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	public static void main(String[] args) {

		Flyway flyway = Flyway.configure()
				.baselineOnMigrate(true)
				.dataSource("jdbc:h2:file:./notedatabase", "name", "pa$$")
				.table("flyway_schema_history")
				.locations("/db/migration")
				.load();
		flyway.migrate();

		SpringApplication.run(Application.class, args);
	}
}
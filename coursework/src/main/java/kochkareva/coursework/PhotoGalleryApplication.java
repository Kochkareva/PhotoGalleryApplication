package kochkareva.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@ServletComponentScan
public class PhotoGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoGalleryApplication.class, args);
	}


	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}

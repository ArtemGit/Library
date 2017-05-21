package by.springboot.testtask;


import by.springboot.testtask.domain.FileContentImpl;
import by.springboot.testtask.domain.LRUCache;
import by.springboot.testtask.reposiroty.FileContentInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
public class LibraryTaskApplication extends SpringBootServletInitializer {



	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(20*1024*1024);
		factory.setMaxRequestSize(20*1024*1024);
		return factory.createMultipartConfig();
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryTaskApplication.class);
	}


	public static void main(String[] args) {

		SpringApplication.run(LibraryTaskApplication.class, args);
	}

	@Bean
	public HttpMessageConverters customConverters() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		return new HttpMessageConverters(arrayHttpMessageConverter);
	}
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}


	@Bean
	public FileContentInterface downloadFileInterface() {
		return new FileContentImpl();
	}

	@Bean
	public LRUCache cacheSupport() {
		return new LRUCache(0);
	}



}

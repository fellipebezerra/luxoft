package br.com.luxoft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

import br.com.luxoft.service.impl.caculatorServiceImp;

@SpringBootApplication
public class LuxoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuxoftApplication.class, args);
	}

	@Bean
	public CommandLineRunner schedulingRunner(TaskExecutor executor) {
	    return new CommandLineRunner() {
	        public void run(String... args) throws Exception {
	            executor.execute(new caculatorServiceImp());
	        }
	    };
	}
	
}

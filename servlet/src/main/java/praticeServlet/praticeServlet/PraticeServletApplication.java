package praticeServlet.praticeServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //서블릿 자동 스캔
@SpringBootApplication
public class PraticeServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraticeServletApplication.class, args);

	}

}

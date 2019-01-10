package ac.cn.saya.springbootpro;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProApplication {

	private static Logger logger = Logger.getLogger(SpringBootProApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProApplication.class, args);
		//项目启动完成打印项目名
		logger.error("服务已经启动 ... ");
		logger.warn("服务已经启动 ... ");
	}

}


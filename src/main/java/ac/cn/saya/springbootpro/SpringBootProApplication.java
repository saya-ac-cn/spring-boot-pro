package ac.cn.saya.springbootpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootProApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootProApplication.class);

	public static void main(String[] args) {
		///SpringApplication.run(SpringBootProApplication.class, args);
		SpringApplication springApplication = new SpringApplication(SpringBootProApplication.class);
		// 禁止命令行设置参数
		springApplication.setAddCommandLineProperties(false);
		springApplication.run(args);
		//项目启动完成打印项目名
		logger.warn("项目已经启动 ... ");
	}

}


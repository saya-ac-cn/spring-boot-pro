package ac.cn.saya.springbootpro;

import ac.cn.saya.springbootpro.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class SpringBootProApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private SceneService sceneService;

	@Test
	public void printTree(){
		sceneService.showScene(1L,true);
	}



}


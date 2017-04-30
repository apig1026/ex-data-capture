package com.ajoshow;

import com.ajoshow.mock.app.MockAppApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={MockAppApplication.class})
public class MockAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}

package com.ajoshow;

import com.ajoshow.mock.server.MockServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={MockServerApplication.class})
public class MockServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}

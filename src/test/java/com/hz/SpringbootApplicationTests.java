package com.hz;

import com.hz.RabbitMQ.RabbitBaseSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	private RabbitBaseSender rabbitBaseSender;

	@Test
	public void rabbitTest() {
		for (int i=0; i < 10; i++) {
			rabbitBaseSender.send();
		}

		for (int i=0; i < 10; i++) {
			rabbitBaseSender.sendTopic1();
		}

		for (int i=0; i < 10; i++) {
			rabbitBaseSender.sendTopic2();
		}
		rabbitBaseSender.sendFanout();
		rabbitBaseSender.sendMap();
	}

}

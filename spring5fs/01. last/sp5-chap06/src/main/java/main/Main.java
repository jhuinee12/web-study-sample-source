package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client;

public class Main {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);

		Client client = ctx.getBean(Client.class);
		client.send();

		ctx.close();
		// ctx.close()가 없다면 컨테이너의 종료 과정을 수행하지 않기 때문에 빈 객체의 소멸 과정도 실행되지 않음
	}

}
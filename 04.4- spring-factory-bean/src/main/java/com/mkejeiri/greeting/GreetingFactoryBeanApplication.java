package com.mkejeiri.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.mkejeiri.greeting.controllers.ConstructorInjectedController;
import com.mkejeiri.greeting.controllers.SetterInjectedController;
import com.mkejeiri.greeting.controllers.MyController;
import com.mkejeiri.greeting.controllers.PropertyInjectedController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mkejeiri.services","com.mkejeiri.greeting"})
public class GreetingFactoryBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingFactoryBeanApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(GreetingFactoryBeanApplication.class, args);

//		MyController controller = (MyController) ctx.getBean("myController");
		MyController controller = (MyController) ctx.getBean(MyController.class);

		System.out.println(controller.hello());
		System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
		System.out.println(ctx.getBean(SetterInjectedController.class).sayHello());
		System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
	}

}

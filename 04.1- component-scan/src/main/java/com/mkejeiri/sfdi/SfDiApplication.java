package com.mkejeiri.sfdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.mkejeiri.sfdi.controllers.ConstructorInjectedController;
import com.mkejeiri.sfdi.controllers.I18nController;
import com.mkejeiri.sfdi.controllers.MyController;
import com.mkejeiri.sfdi.controllers.PetController;
import com.mkejeiri.sfdi.controllers.PropertyInjectorController;
import com.mkejeiri.sfdi.controllers.SetterInjectorController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mkejeiri.services", "com.mkejeiri.sfdi"})
public class SfDiApplication {

	public static void main(String[] args) {

		// ApplicationContext manage all dependencies and config out the box.
		ApplicationContext ctx = SpringApplication.run(SfDiApplication.class, args);
		
		System.out.println("--------- Primary or default Bean");
//		var myController = (MyController) ctx.getBean("myController");
		var myController = (MyController) ctx.getBean(MyController.class);
		//var greeting = myController.SayHello();
		System.out.println("myController greeting - using Primary or default Bean : " + myController.getGreeting());
		//System.out.println("myController greeting (no dependency injection): " + greeting);

		System.out.println("--------- Property");
//		var propertyInjectorController = (PropertyInjectorController) ctx.getBean("propertyInjectorController");
		var propertyInjectorController = (PropertyInjectorController) ctx.getBean(PropertyInjectorController.class);
		System.out.println("propertyInjectorController: " + propertyInjectorController.getGreeting());

		System.out.println("--------- Setter");
//		var setterInjectorController = (SetterInjectorController) ctx.getBean("setterInjectorController");
		var setterInjectorController = (SetterInjectorController) ctx.getBean(SetterInjectorController.class);
		System.out.println("setterInjectorController: " + setterInjectorController.getGreeting());

		System.out.println("--------- Constructor");
//		var constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		var constructorInjectedController = (ConstructorInjectedController) ctx.getBean(ConstructorInjectedController.class);
		System.out.println("contructorInjectedController: " + constructorInjectedController.getGreeting());
		
		
		System.out.println("--------- I18nService");
//		var i18nController = (I18nController) ctx.getBean("i18nController");
		var i18nController = (I18nController) ctx.getBean(I18nController.class);
		System.out.println("i18nController with I18nService for EN, SP, ...: " + i18nController.getGreeting());
		
//		PetController petController = ctx.getBean("petController", PetController.class);
		PetController petController = ctx.getBean(PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());


	}
}

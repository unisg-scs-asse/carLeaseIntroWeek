package io.carlease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarLeaseApplication {

    private static ApplicationContext applicationContext;
    private static final boolean displayAllBeans = false;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(CarLeaseApplication.class, args);
        if (displayAllBeans) {
            displayAllBeans();
        }
    }

    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

}

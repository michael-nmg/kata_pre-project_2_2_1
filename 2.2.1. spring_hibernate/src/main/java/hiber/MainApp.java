package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        Car car = new Car("model1", 111);
        carService.add(car);
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        car = new Car("model2", 222);
        carService.add(car);
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car));
        userService.add(new User("User5", "Lastname5", "user5@mail.ru"));
        car = new Car("model3", 333);
        carService.add(car);
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", car));
        car = new Car("model1", 111);
        carService.add(car);
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", car));

        userService.listUsers().forEach(MainApp::usersMapping);
        userService.listUsersByCar("model1", 111).forEach(MainApp::usersMapping);

        context.close();
    }

    private static void usersMapping(User user) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar());
        }
        System.out.println();
    }
}

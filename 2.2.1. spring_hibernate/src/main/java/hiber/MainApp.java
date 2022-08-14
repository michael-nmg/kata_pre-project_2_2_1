package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("model1", 111)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("model2", 222)));
        userService.add(new User("User5", "Lastname5", "user5@mail.ru"));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("model3", 333)));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("model1", 111)));

        userService.listUsers().forEach(MainApp::usersMapping);

        userService.listUsers("model1", 111).forEach(MainApp::usersMapping);

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

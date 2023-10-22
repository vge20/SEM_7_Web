package com.baeldung.Doctor;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class DoctorSessionFactory {
    private static SessionFactory sessionFactory;

    private DoctorSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties props = new Properties();
                FileInputStream in = new FileInputStream("C:/SEM_7_Web/SEM_7_Web/var3/MedBotSD/src/main/resources/config.properties");
                props.load(in);
                Configuration configuration = new Configuration().configure()
                        .setProperty("hibernate.connection.username", props.getProperty("db_username"))
                        .setProperty("hibernate.connection.password", props.getProperty("db_password"));
                configuration.addAnnotatedClass(DoctorDAModel.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение! " + e);
            }
        }
        return sessionFactory;
    }
}

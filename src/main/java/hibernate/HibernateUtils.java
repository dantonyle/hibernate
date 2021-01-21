package hibernate;

import ecommerce.LaptopEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    static SessionFactory hibernateSessionFactory;
    public static SessionFactory buildSessionFactory() {

        Configuration conf = new Configuration();

        conf.addAnnotatedClass(LaptopEntity.class);
        conf.configure("hibernate.cfg.xml");
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(
                        conf.getProperties()
                ).build();

        hibernateSessionFactory = conf.buildSessionFactory(registry);
        return hibernateSessionFactory;
    }

}

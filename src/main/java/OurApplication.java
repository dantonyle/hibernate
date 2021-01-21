
import ecommerce.LaptopEntity;
import hibernate.HibernateUtils;
import org.hibernate.Session;


public class OurApplication {

    public static void main(String[] args) {
        try {
            hibernateSession = HibernateUtils
                    .buildSessionFactory()
                    .openSession();
            hibernateSession.beginTransaction();


            /* Insert some laptops */


            for (int i = 0; i <= 10; i++) {
                LaptopEntity laptop = new LaptopEntity();
                laptop.setName("SomeBook Series " + (i + 1));
                laptop.setPrice((100F * i) + 500F);
                hibernateSession.save(laptop);
            }


            hibernateSession.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != hibernateSession.getTransaction()) {
                hibernateSession.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (hibernateSession != null) {
                hibernateSession.close();
            }
        }
    }

    static Session hibernateSession;


}

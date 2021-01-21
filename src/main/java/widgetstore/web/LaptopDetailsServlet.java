package widgetstore.web;

import desserts.*;
import ecommerce.LaptopEntity;
import hibernate.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LaptopDetailsServlet extends HttpServlet {

    GenericDAO<DrinkDTO> drinkDAO;

    Session session;

    Long lastID;

    public LaptopDetailsServlet() {
        drinkDAO = new DrinkDAOImpl();
    }

    public void init() {
        session = HibernateUtils.buildSessionFactory().openSession();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LaptopEntity> criteria = builder.createQuery(LaptopEntity.class);
        criteria.from(LaptopEntity.class);
        List<LaptopEntity> laptops = session.createQuery(criteria).getResultList();

        PrintWriter out = response.getWriter();
        out.println("<form action='' method='POST'>");

        out.println("<label>Enter Product ID of laptop below for pricing: <input type='text' name='laptop-id'></input></label>");
        out.println("<input type='submit'>Get Details</input>");
        out.println("<br>");

        /*
        <style>
                table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
        }
        </style>
        */
        out.println("<style>table, th, td { border: 1px solid black; border-collapse: collapse; text-align: center; } </style>");
        out.println("<body>");
        out.println("Available Laptops: ");
        out.println("<table>");
        out.println("<tr><th>Laptop_ID</th><th>Laptop_Name</th></tr>");
        for (LaptopEntity laptop : laptops) {
            //out.println("<br>");

            //ut.println("ID: " + laptop.getId() + "- Name: " + laptop.getName() );
            out.println("<tr><td>" + laptop.getId() + "</td><td>" + laptop.getName() + "</td></tr>");

        }
        out.println("</table>");
        out.println("</body>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException, NumberFormatException {

        response.setContentType("text/html");
        String laptopId = request.getParameter("laptop-id");
        PrintWriter out = response.getWriter();

        try {
            LaptopEntity laptopEntity = session.get(
                    LaptopEntity.class,
                    Long.parseLong(laptopId)
            );


            if (laptopEntity != null) {
                out.println("<form action='' 'method=POST'>");
                out.println("<style>table, th, td { border: 1px solid black; border-collapse: collapse; text-align: center; } </style>");
                out.println("<body>");
                out.println("Product ID entered: " + laptopId);
                out.println("<table>");
                out.println("<tr><th>Laptop_ID</th><th>Laptop_Name</th><th>Laptop_Price</th></tr>");
                out.println("<tr><td>" + laptopId + "</td><td>" + laptopEntity.getName() + "</td><td>" + laptopEntity.getPrice() + "</td></tr>");
                //out.println("The laptop ID# " + laptopId + "- " + laptopEntity.getName() + " is priced at: " + laptopEntity.getPrice());
                out.println("</table>");
                out.println("</body>");
                out.println("</form>");

            } else {
                out.println("No laptop found for id: " + laptopId);
            }

        }catch (NumberFormatException e) {
            response.sendRedirect("laptop");
        }

    }

}

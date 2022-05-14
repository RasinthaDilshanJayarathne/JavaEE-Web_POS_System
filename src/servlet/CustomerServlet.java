package servlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("application/json");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");
            ResultSet rst = connection.prepareStatement("SELECT * FROM Customer").executeQuery();
            String allRecodes="";

            while (rst.next()){
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                String contact = rst.getString(4);
                System.out.println(id+" "+name+" "+address);

                String customer = "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"address\":\"" + address + "\",\"contact\":\"" + contact + "\"},";
                allRecodes = allRecodes + customer;
            }

            String finalJson = "[" + allRecodes.substring(0, allRecodes.length() - 1) + "]";
            PrintWriter writer = resp.getWriter();
            writer.write(finalJson);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("txtPopCustId");
        String customerName = req.getParameter("txtPopCustName");
        String customerAddress = req.getParameter("txtPopCustAddress");
        String customerContact = req.getParameter("txtPopCustPhone");

        System.out.println(customerId + " " + customerName + " " + customerAddress+" "+customerContact);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
            pstm.setObject(1, customerId);
            pstm.setObject(2, customerName);
            pstm.setObject(3, customerAddress);
            pstm.setObject(4, customerContact);
            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.write("Customer Added");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500,e.getMessage());
        }catch (SQLException throwables){
            throwables.printStackTrace();
            resp.sendError(500,throwables.getMessage());
        }
    }
}

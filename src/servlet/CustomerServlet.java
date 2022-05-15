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

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            while (rst.next()){
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                String contact = rst.getString(4);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id",id);
                objectBuilder.add("name",name);
                objectBuilder.add("address",address);
                objectBuilder.add("contact",contact);

                arrayBuilder.add(objectBuilder.build());

            }

            PrintWriter writer = resp.getWriter();

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status",200);
            response.add("message","Done");
            response.add("data",arrayBuilder.build());

            writer.print(response.build());

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

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
            pstm.setObject(1, customerId);
            pstm.setObject(2, customerName);
            pstm.setObject(3, customerAddress);
            pstm.setObject(4, customerContact);

            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());
            }

        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK);
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request Delete");
        String customerId = req.getParameter("CustId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE cusId=?");
            pstm.setObject(1, customerId);

            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.write("Customer Deleted");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500,e.getMessage());
        }catch (SQLException throwables){
            throwables.printStackTrace();
            resp.sendError(500,throwables.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("txtCustId");
        String customerName = req.getParameter("txtCustName");
        String customerAddress = req.getParameter("txtCustAddress");
        String customerContact = req.getParameter("txtCustPhoneNo");

        System.out.println(customerId + " " + customerName + " " + customerAddress+" "+customerContact);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET cusName=?,cusAddress=?,cusTp=? WHERE cusId=?");
            pstm.setObject(1, customerName);
            pstm.setObject(2, customerAddress);
            pstm.setObject(3, customerContact);
            pstm.setObject(4, customerId);
            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.write("Customer Updated");
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

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

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("application/json");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");
            ResultSet rst = connection.prepareStatement("SELECT * FROM Item").executeQuery();

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            while (rst.next()){
                String code = rst.getString(1);
                String name = rst.getString(2);
                String price = rst.getString(3);
                String qtyOnHand = rst.getString(4);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("code",code);
                objectBuilder.add("name",name);
                objectBuilder.add("price",price);
                objectBuilder.add("qtyOnHand",qtyOnHand);

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
        String txtPopItemCode = req.getParameter("txtPopItemCode");
        String txtPopItemName = req.getParameter("txtPopItemName");
        String txtPopItemQuntity = req.getParameter("txtPopItemQuntity");
        String txtPopItemPrice = req.getParameter("txtPopItemPrice");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
            pstm.setObject(1,txtPopItemCode);
            pstm.setObject(2,txtPopItemName);
            pstm.setObject(3,txtPopItemPrice);
            pstm.setObject(4,txtPopItemQuntity);

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
        String itemCode = req.getParameter("code");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE itemCode=?");
            pstm.setObject(1, itemCode);

            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.write("Item Deleted");
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
        String txtItemCode = req.getParameter("txtItemCode");
        String txtItemName = req.getParameter("txtItemName");
        String txtItemQuntity = req.getParameter("txtItemQTYOnHand");
        String txtItemPrice = req.getParameter("txtItemPrice");

        System.out.println(txtItemCode + " " + txtItemName + " " + txtItemQuntity+" "+txtItemPrice);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET itemName=?,unitPrice=?,qtyOnHand=? WHERE itemCode=?");
            pstm.setObject(1,txtItemCode);
            pstm.setObject(2,txtItemName);
            pstm.setObject(3,txtItemQuntity);
            pstm.setObject(4,txtItemPrice);
            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.print("Item Added");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

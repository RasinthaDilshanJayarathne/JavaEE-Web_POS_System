package controller;

import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.impl.BOFactory;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String option = req.getParameter("option");
            resp.setContentType("application/json");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WebSuperMarket", "root", "1234");

            String ItemcCode = req.getParameter("code");

            PrintWriter writer = resp.getWriter();

            switch (option) {
                case "SEARCH":
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item where code=?");
                    preparedStatement.setObject(1,ItemcCode);

                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                    while (resultSet1.next()){
                        String itemCode = resultSet1.getString(1);
                        String itemName = resultSet1.getString(2);
                        String itemPrice = resultSet1.getString(3);
                        String itemQty = resultSet1.getString(4);

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("code", itemCode);
                        objectBuilder.add("name", itemName);
                        objectBuilder.add("price", itemPrice);
                        objectBuilder.add("qtyOnHand", itemQty);
                        arrayBuilder1.add(objectBuilder.build());
                    }

                    JsonObjectBuilder response1 = Json.createObjectBuilder();
                    response1.add("status", 200);
                    response1.add("message", "Done");
                    response1.add("data", arrayBuilder1.build());
                    writer.print(response1.build());

                    break;
                case "GETAll":

                    ObservableList<ItemDTO> allItems = itemBO.getAllItem(connection);
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                    for (ItemDTO c : allItems) {
                        JsonObjectBuilder ob = Json.createObjectBuilder();

                        ob.add("code", c.getCode());
                        ob.add("name", c.getName());
                        ob.add("price", c.getPrice());
                        ob.add("qtyOnHand", c.getQtyOnHand());

                        arrayBuilder.add(ob.build());
                    }

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());
                    break;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        Connection connection = null;

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        try {
            connection = dataSource.getConnection();

            ItemDTO itemDTO = new ItemDTO(
                    jsonObject.getString("code"),
                    jsonObject.getString("name"),
                    Integer.parseInt(jsonObject.getString("price")),
                    Integer.parseInt(jsonObject.getString("qtyOnHand"))
            );

                if (itemBO.addItem(connection, itemDTO)){
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    objectBuilder.add("status", 200);
                    objectBuilder.add("message", "Successfully Added");
                    objectBuilder.add("data", "");
                    writer.print(objectBuilder.build());
                }

            connection.close();

        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 400);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 400);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("code");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = dataSource.getConnection();

            if (itemBO.deleteItem(connection,itemCode)) {

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                objectBuilder.add("message","Customer Successfully Deleted.");
                objectBuilder.add("status",resp.getStatus());
                writer.print(objectBuilder.build());

            }else {

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("message","Wrong Id Inserted.");
                objectBuilder.add("status",400);
                writer.print(objectBuilder.build());

            }
            connection.close();

        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK); //200
            e.printStackTrace();

        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK); //200
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String txtItemCode = jsonObject.getString("code");
        String txtItemName = jsonObject.getString("name");
        String txtItemQuntity = jsonObject.getString("price");
        String txtItemPrice = jsonObject.getString("qtyOnHand");

        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebSuperMarket", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET itemName=?,unitPrice=?,qtyOnHand=? WHERE itemCode=?");
            pstm.setObject(1, txtItemCode);
            pstm.setObject(2, txtItemName);
            pstm.setObject(3, txtItemQuntity);
            pstm.setObject(4, txtItemPrice);

            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Updated");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            } else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("message", "Update Failed");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }

        } catch (ClassNotFoundException e) {

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        } catch (SQLException throwables) {

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }
    }
}

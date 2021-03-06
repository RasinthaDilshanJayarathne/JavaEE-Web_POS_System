package controller;

import bo.custom.PurchaseOrderBO;
import bo.impl.BOFactory;
import dto.OrderDTO;
import dto.OrderDetailDTO;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    private final PurchaseOrderBO orderBO = (PurchaseOrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PURCHASE_ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String option = req.getParameter("option");
            String orderID = req.getParameter("orderID");
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            switch (option){

                case "GETID":

                    JsonObjectBuilder builder = Json.createObjectBuilder();
                    builder.add("orderID",orderBO.generateNewOrderId(connection));
                    writer.print(builder.build());

                    break;

                case "GETALL":

                    ObservableList<OrderDTO> allOrders = orderBO.getAllOrders(connection);
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                        for (OrderDTO ordersDTO : allOrders){

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("orderID", ordersDTO.getOrderId());
                        objectBuilder.add("cusId", ordersDTO.getOrderId());
                        objectBuilder.add("orderDate", String.valueOf(ordersDTO.getOrderDate()));
                        objectBuilder.add("total", ordersDTO.getTotal());
                        objectBuilder.add("subTotal", ordersDTO.getSubTotal());
                        arrayBuilder.add(objectBuilder.build());

                    }

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());

                    break;
            }

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String option = req.getParameter("option");
            String orderId = req.getParameter("orderId");
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            Connection connection = dataSource.getConnection();

            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            JsonArray oDetail = jsonObject.getJsonArray("detail");

            ArrayList<OrderDetailDTO> orderDetailsDTOS = new ArrayList<>();

            for (JsonValue orderDetail: oDetail) {
                JsonObject asJsonObject = orderDetail.asJsonObject();
                orderDetailsDTOS.add(new OrderDetailDTO(
                        asJsonObject.getString("orderId"),
                        asJsonObject.getString("itemCode"),
                        Integer.parseInt(asJsonObject.getString("qty")),
                        Integer.parseInt(asJsonObject.getString("price")),
                        Integer.parseInt(asJsonObject.getString("total"))
                ));
            }

            OrderDTO ordersDTO = new OrderDTO(
                    jsonObject.getString("orderId"),
                    jsonObject.getString("customerId"),
                    LocalDate.parse(jsonObject.getString("date")),
                    Integer.parseInt(jsonObject.getString("total")),
                    Integer.parseInt(jsonObject.getString("subTotal")),
                    orderDetailsDTOS
            );

            if (orderBO.saveOrder(connection, ordersDTO)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status",200);
                objectBuilder.add("data","");
                objectBuilder.add("message","Successfully Added");
                writer.print(objectBuilder.build());
            }else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("data", "Order Not Placed");
                objectBuilder.add("message", "");
                writer.print(objectBuilder.build());
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {

            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            throwables.printStackTrace();

            throwables.printStackTrace();
        }

    }
}

package lk.ijse.dao.impl;

import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.db.DBProcess;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{
    private static final String SAVE_ORDER = "INSERT INTO order_details(order_id,customer_id,date,total) VALUES(?,?,?,?);";

    private static final String SAVE_ORDER_DETAILS = "INSERT INTO order_item(order_id,item_id,qty) VALUES(?,?,?);";

    private static final String GET_ALL_ORDERS = "SELECT * FROM order_details;";

    final static Logger logger = LoggerFactory.getLogger(DBProcess.class);

    public boolean saveOrder(OrderDTO orderDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_ORDER);
            ps.setString(1, orderDTO.getOrder_id());
            ps.setString(2, orderDTO.getCustomer_id());
            ps.setString(3, orderDTO.getDate());
            ps.setString(4, orderDTO.getTotal());

            if (ps.executeUpdate() != 0) {
                logger.info("Order saved successfully");
                System.out.println("Data saved");
                return true;
            } else {
                logger.info("Order saving failed");
                System.out.println("Failed to save");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean saveOrderDetails(OrderDTO orderDTO, Connection connection){
        try {
            var ps = connection.prepareStatement(SAVE_ORDER_DETAILS);
            for (ItemDTO itemDTO : orderDTO.getItems()) {
                ps.setString(1, orderDTO.getOrder_id());
                ps.setString(2, itemDTO.getItem_id());
                ps.setString(3, String.valueOf(itemDTO.getQty()));

                if (ps.executeUpdate() == 0) {
                    logger.info("Order details saving failed");
                    System.out.println("Failed to save");
                    return false;
                }
            }
            logger.info("Order details saved successfully");
            System.out.println("Data saved");
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderDetailsDTO> getAllOrders(Connection connection) {
        List<OrderDetailsDTO> orderDTOS = new ArrayList<>();

        try {
            var ps = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                orderDTOS.add(new OrderDetailsDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderDTOS;
    }



////////////////////////(Transaction)
public void orderTransaction(OrderDTO orderDTO, Connection connection) throws SQLException {
    connection.setAutoCommit(false);

    try {
        var orderdao = new lk.ijse.dao.impl.OrderDAOImpl();
        if (orderdao.saveOrder(orderDTO, connection)) {
            if (orderdao.saveOrderDetails(orderDTO, connection)) {
                connection.setAutoCommit(true);
            } else {
                connection.rollback();
            }
        } else {
            connection.rollback();

        }
    } catch (SQLException e) {
        connection.rollback();
        throw e;
    } finally {
        connection.setAutoCommit(true);
    }

}

}

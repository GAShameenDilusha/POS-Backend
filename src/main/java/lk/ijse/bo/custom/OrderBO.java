package lk.ijse.bo.custom;

import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO {
    boolean saveOrder(OrderDTO orderDTO, Connection connection);

    boolean saveOrderDetails(OrderDTO orderDTO, Connection connection);

    List<OrderDetailsDTO> getAllOrders(Connection connection) throws SQLException;

    void orderTransaction(OrderDTO orderDTO, Connection connection) throws SQLException;
}

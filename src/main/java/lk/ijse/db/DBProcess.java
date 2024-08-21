package lk.ijse.db;

import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBProcess {
    private static final String SAVE_DATA = "INSERT INTO customer(CUSTOMER_ID,NAME,ADDRESS,CONTACT) VALUES (?,?,?,?)";
    private static final String SAVE_ITEM_DATA = "INSERT INTO item(ITEM_ID, DESCR, PRICE, QTY) VALUES (?,?,?,?)";

    private static final String GET_ALL_CUSTOMER_DATA = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET name=? ,address=?, contact=? WHERE customer_id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer_id=?;";
    private static final String GET_ALL_ITEM_DATA = "SELECT * FROM item";
    private static final String UPDATE_ITEM = "UPDATE item SET descr=? ,price=?, qty=? WHERE item_id=?";

    private static final String DELETE_ITEM = "DELETE FROM item WHERE item_id=?;";

    private static final String SAVE_ORDER = "INSERT INTO order_details(order_id,customer_id,date,total) VALUES(?,?,?,?);";

    private static final String SAVE_ORDER_DETAILS = "INSERT INTO order_item(order_id,item_id,qty) VALUES(?,?,?);";

    private static final String GET_ALL_ORDERS = "SELECT * FROM order_details;";


    final static Logger logger = LoggerFactory.getLogger(DBProcess.class);



    public String saveCustomer(CustomerDTO customerDTO, Connection connection) {
        // save / manipulate data
        try {
            var ps = connection.prepareStatement(SAVE_DATA);
            ps.setString(1, customerDTO.getCustomer_id());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getAddress());
            ps.setString(4, customerDTO.getContact());

            if (ps.executeUpdate() != 0) {

                return "Data saved";
            } else {

                return "Failed to save data";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

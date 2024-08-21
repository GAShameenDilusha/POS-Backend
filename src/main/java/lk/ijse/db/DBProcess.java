package lk.ijse.db;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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





    public String saveItem(ItemDTO itemDTO, Connection connection) {
        // save / manipulate data
        try (PreparedStatement ps = connection.prepareStatement(SAVE_ITEM_DATA)) {
            ps.setString(1, itemDTO.getItem_id());
            ps.setString(2, itemDTO.getDescr());
            ps.setDouble(3, itemDTO.getPrice());
            ps.setInt(4, itemDTO.getQty());

            if (ps.executeUpdate() != 0) {
                return "Data saved";
            } else {
                return "Failed to save data";
            }
        } catch (SQLException e) {
            logger.error("Error saving item data", e);
            throw new RuntimeException("Failed to save item data", e);
        }
    }



    public List<CustomerDTO> getCustomerData(Connection connection){
        //get data
        List<CustomerDTO> selectedCustomer = new ArrayList<>();
        try {
            var ps = connection.prepareStatement(GET_ALL_CUSTOMER_DATA);
            var rs = ps.executeQuery();

            while (rs.next()){
                selectedCustomer.add(new CustomerDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            return selectedCustomer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public String updateCustomer(CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_CUSTOMER);

            ps.setString(1, customerDTO.getName());
            ps.setString(2, customerDTO.getAddress());
            ps.setString(3, customerDTO.getContact());
            ps.setString(4, customerDTO.getCustomer_id());

            if (ps.executeUpdate() != 0) {

                return "Data Updates Successfully";
            } else {

                return "Failed to update data";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}

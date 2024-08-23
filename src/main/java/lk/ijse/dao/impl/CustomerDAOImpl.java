package lk.ijse.dao.impl;

import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String SAVE_DATA = "INSERT INTO customer(CUSTOMER_ID,NAME,ADDRESS,CONTACT) VALUES (?,?,?,?)";
    private static final String GET_ALL_CUSTOMER_DATA = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET name=? ,address=?, contact=? WHERE customer_id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer_id=?;";



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

    public String deleteCustomer(CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_CUSTOMER);
            ps.setString(1, customerDTO.getCustomer_id());
            if (ps.executeUpdate() != 0) {

                return "Data Deleted Successfully";
            } else {

                return "Failed to delete data";
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }




}

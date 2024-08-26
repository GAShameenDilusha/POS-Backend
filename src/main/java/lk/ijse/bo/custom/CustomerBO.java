package lk.ijse.bo.custom;

import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO {
    String saveCustomer(CustomerDTO customerDTO, Connection connection) ;

    List<CustomerDTO> getCustomerData(Connection connection) throws SQLException;

    String updateCustomer(CustomerDTO customerDTO, Connection connection);

    String deleteCustomer(CustomerDTO customerDTO, Connection connection);

}

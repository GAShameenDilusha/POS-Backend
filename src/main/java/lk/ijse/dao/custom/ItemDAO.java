package lk.ijse.dao.custom;

import lk.ijse.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {
     String saveItem(ItemDTO itemDTO, Connection connection);

     List<ItemDTO> getItemData(Connection connection) throws SQLException;

     String updateItem(ItemDTO itemDTO, Connection connection);

     String deleteItem(ItemDTO itemDTO, Connection connection);


}

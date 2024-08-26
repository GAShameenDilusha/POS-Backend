package lk.ijse.bo.custom;

import lk.ijse.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO {
    String saveItem(ItemDTO itemDTO, Connection connection);

    List<ItemDTO> getItemData(Connection connection) throws SQLException;

    String updateItem(ItemDTO itemDTO, Connection connection);

    String deleteItem(ItemDTO itemDTO, Connection connection);
}

package lk.ijse.dao.impl;

import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.db.DBProcess;
import lk.ijse.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static final String SAVE_ITEM_DATA = "INSERT INTO item(ITEM_ID, DESCR, PRICE, QTY) VALUES (?,?,?,?)";
    private static final String GET_ALL_ITEM_DATA = "SELECT * FROM item";
    private static final String UPDATE_ITEM = "UPDATE item SET descr=? ,price=?, qty=? WHERE item_id=?";
    private static final String DELETE_ITEM = "DELETE FROM item WHERE item_id=?;";

    final static Logger logger = LoggerFactory.getLogger(DBProcess.class);



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




    public List<ItemDTO> getItemData(Connection connection) {

        //get data
        List<ItemDTO> selectedItem = new ArrayList<>();
        try {
            var ps = connection.prepareStatement(GET_ALL_ITEM_DATA);
            var rs = ps.executeQuery();

            while (rs.next()){
                selectedItem.add(new ItemDTO(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getInt(4)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedItem;

    }




    public String updateItem(ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);

            ps.setString(1, itemDTO.getDescr());
            ps.setDouble(2, itemDTO.getPrice());
            ps.setInt(3, itemDTO.getQty());
            ps.setString(4, itemDTO.getItem_id());

            if (ps.executeUpdate() != 0) {

                return "Data Updates Successfully";
            } else {

                return "Failed to update data";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





    public String deleteItem(ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1, itemDTO.getItem_id());
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

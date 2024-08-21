package lk.ijse.db;

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

}

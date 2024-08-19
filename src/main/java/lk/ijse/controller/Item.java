package lk.ijse.controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.assignment_11_backend.db.DBProcess;
import lk.ijse.assignment_11_backend.dto.CustomerDTO;
import lk.ijse.assignment_11_backend.dto.ItemDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "item", urlPatterns = "/item", initParams = {
        @WebInitParam(name = "db-user", value = "root"),
        @WebInitParam(name = "db-pw", value = "1234"),
        @WebInitParam(name = "db-url", value = "jdbc:mysql://localhost:3306/pos?createDatabaseIfNotExist=true"),
        @WebInitParam(name = "db-class", value = "com.mysql.cj.jdbc.Driver")
})


public class Item extends HttpServlet {
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/pos");
            System.out.println(dataSource);
            this.connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

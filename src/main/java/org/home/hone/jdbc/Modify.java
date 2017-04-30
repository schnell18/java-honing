package org.home.hone.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Modify {

    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://pay-m1-hz.db.pajkdc.com:3306/fundmanager?autoReconnect=true&useUnicode=true&characterEncoding=utf-8",
                "fundmanager",
                "fund29ad0343manager"
            );
            stmt = connection.createStatement();
            String sql = "delete from ";

            stmt.execute(sql);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Oops, class not found exception!");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Oops, SQL exception!");
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();

                if (connection != null)
                    connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

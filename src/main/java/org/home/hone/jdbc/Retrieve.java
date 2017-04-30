package org.home.hone.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Retrieve {

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
            String sql = "select operator_id, gmt_create, source, biz_type, biz_data from manager_oper_log where source in ('CAMEL') order by gmt_create desc limit 10";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                long operatorId = rs.getLong("operator_id");
                String source = rs.getString("source");
                String bizType = rs.getString("biz_type");
                String bizData = rs.getString("biz_data");
                Date gmtCreated = rs.getDate("gmt_create");
                System.out.println(
                    String.format(
                        "%d\t%s\t%s\t%s\t%s\n",
                        operatorId,
                        source,
                        bizType,
                        gmtCreated,
                        bizData
                    )
                );
            }
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

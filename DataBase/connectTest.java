package DataBase;

import java.sql.*;

public class connectTest {
    public static void main(String[] args) {
        //1下载和在工程中配置mysql驱动
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;

        try {
            //2.加载驱动，mysql5之后可不用该语句
            // Class.forName("com.mysql.cj.jdbc.Driver");
            //3.创建连接mysql的url，该语句不用更改；url连接本机可用String url="jdbc:mysql:///db1";
            String url="jdbc:mysql://127.0.0.1:3306/db1?serverTimezone=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            //连接数据库的账号
            String user="root";
            //连接数据库的密码
            String password="MM";
            //4.连接数据库java.sql.Connection
            conn= DriverManager.getConnection(url,user,password);
            //5.测试连接是否成功
            //System.out.println(conn);
            //6.创建sql
            String sql="select * from std where id=?";
            //7.创建操作的对象
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);//设置第一个占位符为1 即id=1

            rs=stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(rs!=null){
                try {
                    rs.close();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }
            }
        }
    }

}

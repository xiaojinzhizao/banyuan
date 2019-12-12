package club.banyuan.bmDemo.servlet;

import club.banyuan.bmDemo.bean.Users;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoadUserServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoadUserServlet.class);

    DataSource ds = null;

    @Override
    public void init() throws ServletException {
        Context ctx = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/banyuan");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("LoadUserServlet doGet");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("LoadUserServlet dopost");
        logger.debug("进入加载用户程序");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        //调用数据库查询
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from users");
            List<Users> users = new ArrayList<Users>();
            while (rs.next()) {
                Users tmpUser = new Users(rs.getInt("userid"),rs.getString("loginname"),
                        rs.getString("password"),rs.getString("name"),rs.getInt("sex"),
                        rs.getInt("age"),rs.getInt("phone"),rs.getString("address"),
                        rs.getInt("authority"));
//                logger.debug("加载成功一个用户:"+rs.getString("name"));
                users.add(tmpUser);
            }

            if(CollectionUtils.isNotEmpty(users)){
                //不为空
                resp.getWriter().write(JSON.toJSONString(users));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }



//        doPost(req, resp);
    }



    /**
     * 得到数据库连接。
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    /**
     * 关闭连接对象。
     */
    protected void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement对象。
     */
    protected void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 关闭PreparedStatement对象。
     */
    protected void closePreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 关闭ResultSet对象。
     */
    protected void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package club.banyuan.bmDemo.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("进入LoginServlet doPost  debug");
        logger.info("进入LoginServlet doPost  info");
        logger.trace("进入LoginServlet doPost trace");
        logger.error("进入LoginServlet doPost error");

        String welcome = getInitParameter("welcome");
        String address = getInitParameter("address");

        String welcome1 = getServletContext().getInitParameter("welcome");
        String address1 = getServletContext().getInitParameter("address");

        logger.debug("welcome"+welcome);
        logger.debug("address"+address);
        logger.debug("welcome1"+welcome1);
        logger.debug("address1"+address1);

        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");

        logger.debug("userName"+userName);
        logger.debug("passWord"+passWord);

        if(StringUtils.equals("Banyuan",userName)){
            resp.sendRedirect("index.html");
        }
        else {
            resp.sendRedirect("404.html");
        }

    }
}

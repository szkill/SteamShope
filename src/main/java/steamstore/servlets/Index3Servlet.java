package steamstore.servlets;

import steamstore.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

@SuppressWarnings("Duplicates")
@WebServlet("/index")
public class Index3Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("SteamStore/index.jsp");
        requestDispatcher.forward(req, resp);

    }

}
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
@WebServlet("/s1")
public class TestSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        LinkedList<Integer> intarray = (LinkedList<Integer>) session.getAttribute("sessionAr");
//
//        if (intarray == null){
//            intarray = new LinkedList<>();
//            session.setAttribute("sessionAr", intarray);
//            intarray.add(5);
//        }
//        else {
//            intarray.add(5);
//        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("SteamStore/productsDota.jsp");
        requestDispatcher.forward(req, resp);

    }


}

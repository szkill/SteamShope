package steamstore.servlets;

import steamstore.service.ItemsService;
import steamstore.service.NewItemException;
import steamstore.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("Duplicates")
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("SteamStore/reg.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = (UserService) getServletContext().getAttribute(UserService.SERVICE_NAME);

        String submit = new String();
        submit = req.getParameter("regSubmit");
        if (submit.equals("active")) {
            String name = req.getParameter("regName");
            String surname = req.getParameter("regSurname");
            String mail = req.getParameter("regMail");
            String pass1 = req.getParameter("regPass1");
            //   req.setAttribute("regStatus", "succes");
//            String pass2 = req.getParameter("regPass2");
            userService.add(name, surname, mail, pass1);
            doGet(req, resp);
        }


    }
}

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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
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
        HttpSession session = req.getSession();

        Boolean isLog = (Boolean) session.getAttribute("isLog");
        if (isLog == null) {
            session.setAttribute("isLog", new Boolean(false));
        }

        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null) {
            session.setAttribute("isAdmin", new Boolean(false));
        }



        String submit = new String();
        if (req.getParameter("regSubmit") != null) {
            submit = req.getParameter("regSubmit");
            if (submit.equals("active")) {
                String name = req.getParameter("regName");
                String surname = req.getParameter("regSurname");
                String mail = req.getParameter("regMail");
                String pass = req.getParameter("regPass");
                //   req.setAttribute("regStatus", "succes");
//            String pass2 = req.getParameter("regPass2");
                if (userService.findUserByMail(mail) != null) {
                    req.setAttribute("loginError", "This mail is already using.");
                    doGet(req, resp);
                    return;
                }
                userService.add(name, surname, mail, pass);
                doGet(req, resp);
            }
        }
        if (req.getParameter("logSubmit") != null) {
            submit = req.getParameter("logSubmit");
            if (submit.equals("active")) {
                String mail = req.getParameter("logMail");
                String pass = req.getParameter("logPass");
                if (userService.findUserByMail(mail) == null) {
                    req.setAttribute("mailError", "Email is incorrect.");
                    doGet(req, resp);
                    return;
                }
                if (!userService.findUserByMail(mail).getPassword().equals(pass)) {
                    req.setAttribute("passError", "Password is incorrect.");
                    doGet(req, resp);
                    return;
                }
                session.setAttribute("isLog", new Boolean(true));
                if (userService.isAdmin(mail)) {
                    session.setAttribute("isAdmin", new Boolean(true));
                    System.out.println("yeah");
                }
                session.setAttribute("UserName", mail);
                doGet(req, resp);
            }
        }


    }

}

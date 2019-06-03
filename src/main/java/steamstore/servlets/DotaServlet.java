package steamstore.servlets;

import steamstore.service.ItemsService;
import steamstore.service.NewItemException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addDota")
public class DotaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addDota.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ItemsService itemsService = (ItemsService) getServletContext().getAttribute(ItemsService.SERVICE_NAME);

        String name = req.getParameter("name");
        String quality = req.getParameter("quality");
        double cost = Double.parseDouble(req.getParameter("cost"));
        String rarity = req.getParameter("rarity");
        String hero = req.getParameter("hero");
        String itemType = req.getParameter("itemType");

        try {
            itemsService.addDotaItem(name, quality, cost, rarity, hero, itemType);
        } catch (
                NewItemException ex) {
            System.out.println(ex.getMessage());
        }
        doGet(req, resp);

//        String name = req.getParameter("name");
//        String password = req.getParameter("pass");
//        Tester user = new Tester(name, password);
//        Model model = Model.getInstance();
//        model.add(user);
//
//        req.setAttribute("userName", name);
//        doGet(req, resp);
    }
}

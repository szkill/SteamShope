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

@SuppressWarnings("Duplicates")
@WebServlet("/filter")
public class FilterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("SteamStore/filter.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ItemsService itemsService = (ItemsService) getServletContext().getAttribute(ItemsService.SERVICE_NAME);

        if (req.getParameter("input") != null) {
            req.setAttribute("DotaItems",
                    itemsService.filterDotaItem(req.getParameter("input"), -1, -1, "", "", "", ""));
        }
        if (req.getParameter("quality") != null) {

            String quality = req.getParameter("quality");
            String rarity = req.getParameter("rarity");
            String hero = req.getParameter("hero");
            String itemType = req.getParameter("itemType");

            System.out.println(quality + "   " + rarity + "   " + hero + " " + itemType);

            itemsService.filterDotaItem("", -1, -1, quality, rarity, hero, itemType)
                    .forEach(dotaItem -> System.out.println(dotaItem));
            req.setAttribute("DotaItems",
                    itemsService.filterDotaItem("", -1, -1, quality, rarity, hero, itemType));
        }
        if (req.getParameter("minCost")!=null && req.getParameter("maxCost")!=null
                && !req.getParameter("minCost").isEmpty() && !req.getParameter("maxCost").isEmpty()) {
            double minCost = Double.parseDouble(req.getParameter("minCost"));
            double maxCost = Double.parseDouble(req.getParameter("maxCost"));
            itemsService.filterDotaItem("",minCost,maxCost,"","","","")
                    .forEach(dotaItem -> System.out.println(dotaItem) );
            req.setAttribute("DotaItems", itemsService.filterDotaItem("",minCost,maxCost,"","","",""));

        }


        doGet(req, resp);
    }
}

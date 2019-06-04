package steamstore.servlets;

import steamstore.json.model.DotaItem;
import steamstore.service.ItemsService;
import steamstore.service.NewItemException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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

        List<DotaItem> base = new LinkedList<>();

        if (req.getParameter("input") != null) {
            base = itemsService.filterDotaItem(req.getParameter("input"), -1, -1, "", "", "", "");
        }
        if (req.getParameter("quality") != null) {

            String quality = req.getParameter("quality");
            String rarity = req.getParameter("rarity");
            String hero = req.getParameter("hero");
            String itemType = req.getParameter("itemType");

            System.out.println(quality + "   " + rarity + "   " + hero + " " + itemType);

            itemsService.filterDotaItem("", -1, -1, quality, rarity, hero, itemType)
                    .forEach(dotaItem -> System.out.println(dotaItem));

            base = itemsService.filterDotaItem("", -1, -1, quality, rarity, hero, itemType);
        }

        if (base != null) {
            LinkedList<DotaItem> tripleItem = null;
            LinkedList<LinkedList<DotaItem>> allitems = new LinkedList<LinkedList<DotaItem>>();

            int i = 0;
            for (DotaItem elem :
                    base) {
                if ((i % 3) == 0) {
                    tripleItem = new LinkedList<DotaItem>();
                    allitems.add(tripleItem);
                }
                tripleItem.add(elem);

                i++;
            }

            req.setAttribute("dotaitems3Buff", allitems);

        }

        doGet(req, resp);
    }
}

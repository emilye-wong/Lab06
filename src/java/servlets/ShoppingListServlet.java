package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user = request.getParameter("user");
        String action = request.getParameter("action");

        if (action != null && action.equals("logout")) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }

        if (user != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArrayList<String> itemsList;
        String user = request.getParameter("user");
        String action = request.getParameter("action");
        String itemInput = request.getParameter("item");
        String deleteItem = request.getParameter("item");

        if (session.getAttribute("itemsList") == null) {
            itemsList = new ArrayList<>();
        } else {
            itemsList = (ArrayList<String>) session.getAttribute("itemsList");
        }

        // registering user
        if (action != null && action.equals("register")) {
            if (user != null && !user.isEmpty()) {
                // setting the user attribute
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else {
                request.setAttribute("userNull", true);
                // if there is not value in the input
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }

        } // adding items to the list
        else if (action != null && action.equals("add")) {
            // displays the delete button
            request.setAttribute("deleteButton", true);
            if (itemInput != null && !itemInput.isEmpty()) {
                // displays the delete button
                request.setAttribute("deleteButton", true);
                // adding the item to the arraylist
                itemsList.add(itemInput);
                // connecting the list to the array
                session.setAttribute("itemsList", itemsList);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else {
                request.setAttribute("itemNull", true);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        } // deleting an item
        else if (action != null && action.equals("delete")) {
            // displays the delete button
            request.setAttribute("deleteButton", true);
            itemsList.remove(deleteItem);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
    }
}

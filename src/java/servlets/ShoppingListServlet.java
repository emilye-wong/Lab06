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
        String user = request.getParameter("user");
        String action = request.getParameter("action");

        // registering user
        if (action != null && action.equals("register")) {
            if (user != null || !user.equals("")) {
                // setting the user attribute
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("userNull", true);
            // if there is not value in the input
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }

        // adding items to the list
        ArrayList<String> itemsList = (ArrayList<String>) session.getAttribute("itemsList");
        String itemInput = request.getParameter("item");

        if (action != null && action.equals("add")) {
            // displays the delete button
            request.setAttribute("deleteButton", true);
            if (itemInput != null || !itemInput.equals("")) {
                // adding the item to the arraylist
                itemsList.add(itemInput);
                // connecting the list to the array
                session.setAttribute("itemsList", itemsList);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else {
                request.setAttribute("itemNull", true);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        }

        // deleting an item
        String deleteItem = request.getParameter("item");

        if (action != null && action.equals("delete")) {
            itemsList.remove(deleteItem);
             getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            //response.sendRedirect("ShoppingList");
        }
    }
}

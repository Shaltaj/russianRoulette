package com.github.molodtsov.russianRoulette.web;

import com.github.molodtsov.russianRoulette.dao.GameDAOImpl;
import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/players/register")
public class RegisterPlayerServlet extends HttpServlet {
    private PlayerDAO playerDAO = new PlayerDAOImpl(ApplicationListener.getEntityManager());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/player-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (name == null) {
            throw new IllegalArgumentException("name missing");
        }
        if (login == null) {
            throw new IllegalArgumentException("login missing");
        }
        if (password == null) {
            throw new IllegalArgumentException("password missing");
        }
        playerDAO.RegisterPlayer(name, login, password);
        resp.sendRedirect("/players/top");
    }

}

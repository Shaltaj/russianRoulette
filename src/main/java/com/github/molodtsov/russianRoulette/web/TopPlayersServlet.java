package com.github.molodtsov.russianRoulette.web;

import com.github.molodtsov.russianRoulette.dao.GameDAOImpl;
import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;
import com.github.molodtsov.russianRoulette.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/players/top"})
public class TopPlayersServlet extends HttpServlet {
    private PlayerDAO playerDAO = new PlayerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerListBean bean = new PlayerListBean(req.getHeader("User-Agent"), playerDAO.TopPlayers(10));
        req.setAttribute("bean", bean);
        req.getRequestDispatcher("/pages/player-top-list.jsp")
                .forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        if (playerDAO.TopPlayers(1).size() == 0) {
            playerDAO.RegisterPlayer("admin", "admin", "123456");
        }
    }
}

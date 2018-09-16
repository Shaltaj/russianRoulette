package com.github.molodtsov.russianRoulette.web;

import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayersController {
    private final PlayerDAO playerDAO;

    @Autowired
    public PlayersController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/players/top")
    public String topPlayerFormGet(@RequestHeader("User-Agent") String userAgent, ModelMap model) {
        PlayerListBean bean = new PlayerListBean(userAgent, playerDAO.topPlayers(10));

        model.put("bean", bean);

        return "player-top-list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/players/register")
    public String registerPlayerFormGet(ModelMap model) {
        return "player-register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/players/register")
    public String registerPlayerFormPost(@RequestParam String name,
                                         @RequestParam String login,
                                         @RequestParam String password,
                                         @RequestHeader("User-Agent") String userAgent,
                                         ModelMap model) {

        if (name == null) {
            throw new IllegalArgumentException("name missing");
        }
        if (login == null) {
            throw new IllegalArgumentException("login missing");
        }
        if (password == null) {
            throw new IllegalArgumentException("password missing");
        }
        playerDAO.registerPlayer(name, login, password);

        return topPlayerFormGet(userAgent, model);
    }

}

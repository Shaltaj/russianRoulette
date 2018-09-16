package com.github.molodtsov.russianRoulette.web;

import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

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

    @ModelAttribute("formBean")
    public RegisterPlayerFormBean createFormBean() {
        return new RegisterPlayerFormBean();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/players/register")
    public String registerPlayerFormGet(ModelMap model) {
        return "player-register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/players/register")
    public String registerPlayerFormPost(@Valid @ModelAttribute("formBean") RegisterPlayerFormBean formBean,
                                         BindingResult binding) {

//        if (formBean.getName().isEmpty()) {
//            binding.addError(new FieldError("formBean", "name", "name is empty"));
//        }
//        if (formBean.getLogin().isEmpty()) {
//            binding.addError(new FieldError("formBean", "login", "login is empty"));
//        }
//        if (formBean.getPassword().isEmpty()) {
//            binding.addError(new FieldError("formBean", "password", "password is empty"));
//        }
        if (binding.hasErrors()) {
            return "player-register";

        }
        try {
            playerDAO.registerPlayer(formBean.getName(), formBean.getLogin(), formBean.getPassword());
        } catch (PersistenceException e) {
            binding.addError(new FieldError("formBean", "login", "login already exist"));
            return "player-register";
        }

        return "redirect:/players/top";
    }

}

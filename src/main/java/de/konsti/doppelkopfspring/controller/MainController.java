package de.konsti.doppelkopfspring.controller;

import de.konsti.doppelkopfspring.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        Object attr = session.getAttribute("game");

        if (attr != null) {
            Game game = (Game) attr;
            model.addAttribute("game", game);
            return "game";
        }

        return "players";
    }

    @PostMapping("/players")
    public String players(Model model, HttpSession session, @RequestParam("player1") String player1,
                          @RequestParam("player2") String player2,
                          @RequestParam("player3") String player3,
                          @RequestParam("player4") String player4,
                          @RequestParam("player5") String player5,
                          @RequestParam(name = "fivePlayer", defaultValue = "false") boolean fivePlayer) {

        Game game = new Game(fivePlayer, player1, player2, player3, player4, player5);

        session.setAttribute("game", game);
        return "redirect:/";
    }

    @PostMapping("/newGame")
    public String newGame(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/addRound")
    public String addRound(Model model, HttpSession session, @RequestParam(name = "solo", defaultValue = "false") boolean solo,
                           @RequestParam(name = "soloWon", defaultValue = "false") boolean soloWon,
                           @RequestParam(name = "bock", defaultValue = "false") boolean bock,
                           @RequestParam(name = "points") int points,
                           @RequestParam(name = "player1") int player1,
                           @RequestParam(name = "player2") int player2) {
        Game game = (Game) session.getAttribute("game");
        game.addRound(solo, soloWon, bock, points, player1, player2);
        return "redirect:/";
    }

    @PostMapping("deleteRound")
    public String deleteRound(Model model, HttpSession session){
        Game game = (Game) session.getAttribute("game");
        game.deleteRound();
        return "redirect:/";
    }

}

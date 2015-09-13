package com.deviget.web;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deviget.model.Game;
import com.deviget.model.Move;

@Controller
public class Connect4Controller {

	
	@RequestMapping("/game/{playerNumber:[1-2]}")
	public String game(ModelMap model, @PathVariable Integer playerNumber) {
		Game game = Game.getInstance();
		model.addAttribute("playerId", playerNumber);
		if (playerNumber.intValue() == 1) {
			model.addAttribute("color", game.getPlayer1().getColor().toString());
		} else {
			model.addAttribute("color", game.getPlayer2().getColor().toString());

		}
		return "game";

	}
		
		@RequestMapping(value="/newGame",method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
		  public String newGame (ModelMap model,@RequestParam("player") Integer player) { 
		   Game game =  Game.getInstance().newGame ();
		   JSONObject response = new JSONObject();
		   response.put("rows", game.getBoard().ROWS);
		   response.put("columns", game.getBoard().COLUMNS);
		   response.put("playerTurn", game.getPlayerTurn().getId());
		   return response.toString();
		  
		}
		
		@RequestMapping(value = "/makeMove", method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
		public String makeMove(@RequestParam("player") Integer playerNumber, @RequestParam("row") Integer row,
				@RequestParam("col") Integer column) {
			Game game = Game.getInstance();
			boolean validMove = game.makeMove(playerNumber, row, column);
			JSONObject response = new JSONObject();
			response.put("validMove", validMove);
			return response.toString();
		}
		
		@RequestMapping(value = "/gameStatus", method = RequestMethod.GET, produces="application/json")
		@ResponseBody
		public String getGameStatus() { 
			Game game = Game.getInstance();
			JSONObject response = new JSONObject();
			Move lastMove = game.getLastMove ();
			if (lastMove!=null ){
				response.put("lastMoveCol", game.getLastMove ().getColumn ());
				response.put("lastMoveRow", game.getLastMove ().getRow ());
				response.put("lastMovePlayer", game.getLastMove().getPlayer().getId());
				response.put("lastMoveColor", game.getLastMove().getPlayer().getColor());
					
			}
			response.put("status", game.getStatus ());
	       	return response.toString();
		}
		
	}

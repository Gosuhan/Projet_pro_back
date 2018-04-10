package co.simplon.simplonclick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.service.MembreService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class MembreController {
	
	@Autowired
	private MembreService membreService;

	//SELECT * FROM membre;
	@GetMapping(path = "/membres")
	public @ResponseBody Iterable<Membre> getAllMembres() throws Exception {
		return membreService.getAllMembres();
	}

}

package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/persona")
public class PersonaController {
	@Autowired
	private PersonaRepository personaRepository;
	
	@RequestMapping("/all")
	public Iterable<Persona> getPersona() {
        return personaRepository.findAll();
    }
}

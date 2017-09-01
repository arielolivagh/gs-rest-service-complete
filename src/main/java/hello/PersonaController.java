package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/persona")
public class PersonaController {
	@Autowired
	private PersonaRepository personaRepository;
	
	@RequestMapping("/add")
	public String addPersona(@RequestParam  String nombre,@RequestParam  int edad,@RequestParam  String sexo) {
		Persona p = new Persona();
		p.setNombre(nombre);
		p.setEdad(edad);
		p.setSexo(sexo);
		personaRepository.save(p);
		
        return "Agregado correctamente";
    }
	
	@RequestMapping("/all")
	public Iterable<Persona> getPersona() {
        return personaRepository.findAll();
    }
	
	@RequestMapping("/allBySexo")
	public Iterable<Persona> getPersonaBySexo(@RequestParam  String sexo) {
        return personaRepository.findBySexo(sexo);
    }
	
	@RequestMapping("/delete")
	public String deletePersona(@RequestParam int id) {
		Persona p = new Persona();
		p.setIdPersona(id);
		personaRepository.delete(p);
        return "Borrado correctamente";
    }
	
	@RequestMapping("/total")
	public long totalPersona() {	
        return personaRepository.count();
    }
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addPersonaPost(@RequestParam  String nombre,@RequestParam  int edad,@RequestParam  String sexo) {
		Persona p = new Persona();
		p.setNombre(nombre);
		p.setEdad(edad);
		p.setSexo(sexo);
		personaRepository.save(p);
		
        return "Agregado correctamente";
    }
	
	@RequestMapping(value="/sexo",headers={"sexo=M"})
	public Iterable<Persona> personaM() {
        return personaRepository.findBySexo("M");
    }
	
	@RequestMapping(value="/sexo",headers={"sexo=F"})
	public Iterable<Persona> personaF() {
        return personaRepository.findBySexo("F");
    }
	
	@RequestMapping(value="/sexoPag")
	public Page<Iterable<Persona>> personaPag(@RequestParam String sexo,@RequestParam  int inicio,@RequestParam  int bloque) {
        return personaRepository.findBySexoPag(sexo, new PageRequest(inicio, bloque));
    }
	
	@RequestMapping(value="/sexoOrder")
	public Page<Iterable<Persona>> personaOrder(@RequestParam String sexo,@RequestParam  int inicio,@RequestParam  int bloque,@RequestParam  String orden) {
        return personaRepository.findBySexoPag(sexo, new PageRequest(inicio, bloque,Sort.Direction.ASC, orden));
    }
}

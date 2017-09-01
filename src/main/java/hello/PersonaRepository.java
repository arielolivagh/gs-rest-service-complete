package hello;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonaRepository extends CrudRepository<Persona, Long>{
	@Query("select p from Persona p where p.sexo = ?1")
	Iterable<Persona> findBySexo(String sexo);
	
	@Query("select p from Persona p where p.sexo = ?1")
	Page<Iterable<Persona>> findBySexoPag(@Param("sexo")  String sexo,Pageable pageable);
}

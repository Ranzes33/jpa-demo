package com.example;

import com.example.model.Categoria;
import com.example.model.Vacante;
import com.example.repository.CategoriasRepository;
import com.example.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repoCategorias;

	@Autowired
	private VacantesRepository repoVacantes;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		buscarVacantes();
	}
	//metodos de crud repository
	private void modificar(){
		Optional<Categoria> optional = repoCategorias.findById(1);
		if(optional.isPresent()){
			Categoria catTmp= optional.get();
			catTmp.setNombre("Ing. de software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repoCategorias.save(catTmp);
			System.out.println(optional.get());
		}
		else System.out.println("Cateooria no encontrada");
	}
	private void buscarPorId(){
		Optional<Categoria> optional = repoCategorias.findById(1);
		if(optional.isPresent()) System.out.println(optional.get());
		else System.out.println("Cateooria no encontrada");
	}
	private void encontrarPorIds(){
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
		for(Categoria cat : categorias){
			System.out.println(cat);
		}
	}
	private void buscarTodos(){
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for (Categoria cat :categorias){
			System.out.println(cat);
		}
	}
	private void guardar(){
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y seguridad");
		repoCategorias.save(cat);
		System.out.println("cat = " + cat);
	}
	private void conteo(){
		long count= repoCategorias.count();
		System.out.println("total categorias = " + count);
	}
	private void eliminar(){
		int idCategoria = 1;
		repoCategorias.deleteById(idCategoria);
		System.out.println("Eliminando un registro");
	}
	private void eliminarTodos(){
		repoCategorias.deleteAll();
	}
	private void existeId(){
		boolean existe = repoCategorias.existsById(5);
		System.out.println("La categorias existe?: " + existe);
	}

	//metodos de jpaRepository
	private void buscarTodosJPA(){
		List<Categoria> categorias = repoCategorias.findAll();
		for(Categoria c : categorias){
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	private void borrarTodoEnBloque(){
		repoCategorias.deleteAllInBatch();
	}
	private void buscarTodosOrdenados(){
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());
		for(Categoria c : categorias){
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	private void buscarTodosPaginacion(){
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(2,6));
		System.out.println("Total de registros: " + page.getTotalElements());
		System.out.println("Total de paginas: " + page.getTotalPages());
		for (Categoria c : page.getContent()){
			System.out.println(c.getId() + " " +c.getNombre());
		}
	}
	private void buscarTodosPaginacionOrdenados(){
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0,5, Sort.by("nombre").descending()));
		System.out.println("Total de registros: " + page.getTotalElements());
		System.out.println("Total de paginas: " + page.getTotalPages());
		for (Categoria c : page.getContent()){
			System.out.println(c.getId() + " " +c.getNombre());
		}
	}

	private void buscarVacantes(){
		List<Vacante> lista = repoVacantes.findAll();
		for (Vacante vacante: lista) {
			System.out.println(vacante);
		}
	}
}

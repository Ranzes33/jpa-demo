package com.example;

import com.example.model.Categoria;
import com.example.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		guardar();
	}
	private void modificar(){
		Optional<Categoria> optional = repo.findById(1);
		if(optional.isPresent()){
			Categoria catTmp= optional.get();
			catTmp.setNombre("Ing. de software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repo.save(catTmp);
			System.out.println(optional.get());
		}
		else System.out.println("Cateooria no encontrada");
	}
	private void buscarPorId(){
		Optional<Categoria> optional = repo.findById(1);
		if(optional.isPresent()) System.out.println(optional.get());
		else System.out.println("Cateooria no encontrada");
	}
	private void encontrarPorIds(){
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repo.findAllById(ids);
		for(Categoria cat : categorias){
			System.out.println(cat);
		}
	}
	private void buscarTodos(){
		Iterable<Categoria> categorias = repo.findAll();
		for (Categoria cat :categorias){
			System.out.println(cat);
		}
	}
	private void guardar(){
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y seguridad");
		repo.save(cat);
		System.out.println("cat = " + cat);
	}
	private void conteo(){
		long count= repo.count();
		System.out.println("total categorias = " + count);
	}
	private void eliminar(){
		int idCategoria = 1;
		repo.deleteById(idCategoria);
		System.out.println("Eliminando un registro");
	}
	private void eliminarTodos(){
		repo.deleteAll();
	}
	private void existeId(){
		boolean existe = repo.existsById(5);
		System.out.println("La categorias existe?: " + existe);
	}

}

package com.example.demo;

import com.example.model.Categoria;
import com.example.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
		buscarPorId();
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

	private void guardar(){
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y seguridad");
		repo.save(cat);
		System.out.println("cat = " + cat);
	}

	private void eliminar(){
		System.out.println("Eliminando un registro");
	}
}

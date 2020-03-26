package br.com.ifspgru.repositoryy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
import br.com.ifspgru.repository.entity.UsuarioEntity;

public class UsuarioRepository {

	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
 
	public UsuarioRepository(){
 
		/*CRIANDO O NOSSO EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_estudo");
 
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void Salvar(UsuarioEntity usuarioEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(usuarioEntity);
		this.entityManager.getTransaction().commit();
	}

	public void Alterar(UsuarioEntity usuarioEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(usuarioEntity);
		this.entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> TodosUsuarios(){
 
		return this.entityManager.createQuery("SELECT p FROM UsuarioEntity p ORDER BY p.nome").getResultList();
	}

	public UsuarioEntity GetUsuario(Integer codigo){
 
		return this.entityManager.find(UsuarioEntity.class, codigo);
	}

	public void Excluir(Integer codigo){
 
		UsuarioEntity usuario = this.GetUsuario(codigo);
 
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(usuario);
		this.entityManager.getTransaction().commit();
 
	}
	
}

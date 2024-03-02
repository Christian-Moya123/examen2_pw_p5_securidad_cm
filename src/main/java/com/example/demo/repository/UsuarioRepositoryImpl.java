package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.modelo.Usuario;


@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT u FROM Usuario u WHERE u.username = :username";
		
		TypedQuery<Usuario> myQuery = this.entityManager.createQuery(sql, Usuario.class);
		myQuery.setParameter("username", username);
		return myQuery.getSingleResult();
		
//		Usuario u  = new Usuario();
//		u.setUsername("admin");
//		u.setPassword("pasword encriptado");
//		
//		return u;
	}
	
	

}

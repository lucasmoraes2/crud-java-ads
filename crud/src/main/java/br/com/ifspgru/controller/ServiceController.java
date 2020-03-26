package br.com.ifspgru.controller;

import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
import br.com.ifspgru.http.Usuario;
import br.com.ifspgru.repositoryy.UsuarioRepository;
import br.com.ifspgru.repository.entity.UsuarioEntity;

@Path("/service")
public class ServiceController {

		private final  UsuarioRepository repository = new UsuarioRepository();
	 
		@POST	
		@Consumes("application/json; charset=UTF-8")
		@Produces("application/json; charset=UTF-8")
		@Path("/cadastrar")
		public String Cadastrar(Usuario usuario){
	 
			UsuarioEntity entity = new UsuarioEntity();
	 
			try {
	 
				entity.setNome(usuario.getNome());
				entity.setSexo(usuario.getSexo());
	 
				repository.Salvar(entity);
	 
				return "Registro cadastrado com sucesso!";
	 
			} catch (Exception e) {
	 
				return "Erro ao cadastrar um registro " + e.getMessage();
			}
	 
		}

		@PUT
		@Produces("application/json; charset=UTF-8")
		@Consumes("application/json; charset=UTF-8")	
		@Path("/alterar")
		public String Alterar(Usuario usuario){
	 
			UsuarioEntity entity = new UsuarioEntity();
	 
			try {
	 
				entity.setCodigo(usuario.getCodigo());
				entity.setNome(usuario.getNome());
				entity.setSexo(usuario.getSexo());
	 
				repository.Alterar(entity);
	 
				return "Registro alterado com sucesso!";
	 
			} catch (Exception e) {
	 
				return "Erro ao alterar o registro " + e.getMessage();
	 
			}
	 
		}

		@GET
		@Produces("application/json; charset=UTF-8")
		@Path("/todasUsuarios")
		public List<Usuario> TodasUsuarios(){
	 
			List<Usuario> usuarios =  new ArrayList<Usuario>();
	 
			List<UsuarioEntity> listaEntityUsuarios = repository.TodosUsuarios();
	 
			for (UsuarioEntity entity : listaEntityUsuarios) {
	 
				usuarios.add(new Usuario(entity.getCodigo(), entity.getNome(),entity.getSexo()));
			}
	 
			return usuarios;
		}

		@GET
		@Produces("application/json; charset=UTF-8")
		@Path("/getUsuario/{codigo}")
		public Usuario GetUsuario(@PathParam("codigo") Integer codigo){
	 
			UsuarioEntity entity = repository.GetUsuario(codigo);
	 
			if(entity != null)
				return new Usuario(entity.getCodigo(), entity.getNome(),entity.getSexo());
	 
			return null;
		}

		@DELETE
		@Produces("application/json; charset=UTF-8")
		@Path("/excluir/{codigo}")	
		public String Excluir(@PathParam("codigo") Integer codigo){
	 
			try {
	 
				repository.Excluir(codigo);
	 
				return "Registro excluido com sucesso!";
	 
			} catch (Exception e) {
	 
				return "Erro ao excluir o registro! " + e.getMessage();
			}
	 
		}
	
}

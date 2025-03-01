package controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable String id) {
        Usuario usuario = new Usuario();
        usuario.setId(Long.valueOf(id));
        usuario.setNombre("rodrigo");
        usuario.setApellido("bonetto");
        usuario.setEmail("rodrigo@gmail.com");
        usuario.setTelefono("33465773");
        return usuario;
    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario ) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
       usuarioDao.registrar(usuario);
    }
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Autorization") String token) {
       if(!validarToken(token)) { return null;}

           return usuarioDao.getUsuarios();
       }





private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
}


    @RequestMapping(value = "usuario123")
    public Usuario editar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("rodrigo");
        usuario.setApellido("bonetto");
        usuario.setEmail("rodrigo@gmail.com");
        usuario.setTelefono("33465773");
        // Agrega aquí la lógica para editar el usuario
        return usuario;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Autorization") String token,
                           @PathVariable Long id) {
        if(!validarToken(token)) { return ; }
       usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuario234")
    public Usuario buscar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("rodrigo");
        usuario.setApellido("bonetto");
        usuario.setEmail("rodrigo@gmail.com");
        usuario.setTelefono("33465773");
        // Agrega aquí la lógica para eliminar el usuario
        return usuario;
    }
}

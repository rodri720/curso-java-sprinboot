async function cargarUsuarios() {
    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const usuarios = await request.json();

    let botonEliminar = '<a href="#" onclick="eliminarUsuario(23456)" class="btn btn-danger btn-circle btn-lg"><i class="fas fa-trash"></i></a>'

    // Verificar si la respuesta es un arreglo
    if (Array.isArray(usuarios)) {
     let botonEliminar = '<a href="#" onclick="eliminarUsuario('+ usuario.id +')" class="btn btn-danger btn-circle btn-lg"><i class="fas fa-trash"></i></a>'
        let listadoHtml = '';
        let telefono = usuario.telefono == null? '-' : usuario.telefono;
        usuarios.forEach(usuario => {
            let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>' + usuario.email + '</td><td>' + usuario.telefono + '</td><td>'+ botonEliminar +'</td></tr>';
            listadoHtml += usuarioHtml;
        });

        console.log(usuarios);
        document.querySelector('#usuarios tbody').innerHTML = listadoHtml;
    } else {
        console.error('La respuesta no es un arreglo:', usuarios);
    }
}
async function eliminarUsuario(id)  {

if(!confirm('?desea eliminar este usuario?')){
return;
}
 const request = await fetch('api/usuarios' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
   location.reload();
}
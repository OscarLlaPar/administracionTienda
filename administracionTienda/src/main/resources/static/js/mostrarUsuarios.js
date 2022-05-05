let contenidoTabla = document.getElementById("contenidoTabla")

fetch("http://localhost:8085/api/usuarios?nombre=")
	.then(response => response.json())
	.then(result =>{
		for(let usuario of result){
			let filaTabla=document.createElement("tr");
			let celda1=document.createElement("td");
			celda1.innerHTML=usuario.nombre;
			filaTabla.appendChild(celda1);
			let celda2=document.createElement("td");
			celda2.innerHTML=usuario.apellido1;
			filaTabla.appendChild(celda2);
			let celda3=document.createElement("td");
			celda3.innerHTML=usuario.apellido2;
			filaTabla.appendChild(celda3);
			let celda4=document.createElement("td");
			celda4.innerHTML=usuario.email;
			filaTabla.appendChild(celda4);
			let celda5=document.createElement("td");
			let enlaceRol=document.createElement("a");
			enlaceRol.innerHTML=usuario.rol.rol;
			switch(usuario.rol.rol){
				case "Administrador": enlaceRol.setAttribute("href", "/login/administradores");
					break;
				case "Empleado": enlaceRol.setAttribute("href", "/login/empleados");
					break;
				case "Cliente": enlaceRol.setAttribute("href", "/login/clientes");
			}
			celda5.appendChild(enlaceRol);
			filaTabla.appendChild(celda5);
			console.log(usuario);
			contenidoTabla.appendChild(filaTabla);
		}
	})
	.catch(err => console.log(err))
	
let busqueda=document.getElementById("busqueda");

busqueda.addEventListener("keyup", function(event){
	fetch("http://localhost:8085/api/usuarios?nombre="+busqueda.value)
	.then(response => response.json())
	.then(result =>{
		contenidoTabla.innerHTML="";
		for(let usuario of result){
			let filaTabla=document.createElement("tr");
			let celda1=document.createElement("td");
			celda1.innerHTML=usuario.nombre;
			filaTabla.appendChild(celda1);
			let celda2=document.createElement("td");
			celda2.innerHTML=usuario.apellido1;
			filaTabla.appendChild(celda2);
			let celda3=document.createElement("td");
			celda3.innerHTML=usuario.apellido2;
			filaTabla.appendChild(celda3);
			let celda4=document.createElement("td");
			celda4.innerHTML=usuario.email;
			filaTabla.appendChild(celda4);
			let celda5=document.createElement("td");
			let enlaceRol=document.createElement("a");
			enlaceRol.innerHTML=usuario.rol.rol;
			switch(usuario.rol.rol){
				case "Administrador": enlaceRol.setAttribute("href", "/login/administradores");
					break;
				case "Empleado": enlaceRol.setAttribute("href", "/login/empleados");
					break;
				case "Cliente": enlaceRol.setAttribute("href", "/login/clientes");
			}
			celda5.appendChild(enlaceRol);
			filaTabla.appendChild(celda5);
			console.log(usuario);
			contenidoTabla.appendChild(filaTabla);
		}
	})
	.catch(err => console.log(err))
})
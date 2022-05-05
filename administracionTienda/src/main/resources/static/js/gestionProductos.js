fetch("http://localhost:8085/api/productos?nombre=")
	.then(response => response.json())
	.then(result =>{
		console.log(result);
		rellenarLista();
	})
	.catch(err => console.log(err))
	
let busqueda=document.getElementById("busqueda");
busqueda.addEventListener("keyup",function(event){
	fetch("http://localhost:8085/api/productos?nombre="+busqueda.value)
	.then(response => response.json())
	.then(result =>{
		console.log(result);
	})
	.catch(err => console.log(err))
})

function rellenarLista(){
	let lista=document.getElementById("lista");
		let item=document.createElement("div");
		lista.appendChild(item);
		console.log(item);
		item.classList.add("list-group-item");
		
			let form=document.createElement("form");
			item.appendChild(form);
			form.setAttribute("action", "productos/editar");
			form.setAttribute("th:object", "${productoEnCurso}");
			form.setAttribute("method", "post");
			
				let container=document.createElement("div");
				form.appendChild(container);
				container.classList.add("container");
				
					
				
			
		
	
	
}
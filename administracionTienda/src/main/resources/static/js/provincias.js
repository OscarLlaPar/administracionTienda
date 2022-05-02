function obtenerProvincias(){
	fetch("https://public.opendatasoft.com/api/records/1.0/search/?dataset=provincias-espanolas&q=&sort=provincia&facet=provincia")
		.then(response => response.json())
		.catch(err => console.log(err))
}
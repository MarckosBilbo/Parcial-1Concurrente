const svg = d3.select("svg");
const width = +svg.attr("width");
const height = +svg.attr("height");

// Función para obtener los datos de los componentes desde el backend
async function obtenerDatos(url) {
    const response = await fetch(url);
    return await response.json();
}

// Función para dibujar los clavos
async function dibujarClavos() {
    const clavos = await obtenerDatos('/api/componentes/clavos');
    svg.selectAll("circle.clavo")
        .data(clavos)
        .enter().append("circle")
        .attr("class", "clavo")
        .attr("cx", (d, i) => 200 + (i % 5) * 100)  // Ajustar según sea necesario
        .attr("cy", (d, i) => 50 + Math.floor(i / 5) * 50)  // Ajustar según sea necesario
        .attr("r", 5)
        .style("fill", "black");
}

// Función para dibujar los contenedores
async function dibujarContenedores() {
    const contenedores = await obtenerDatos('/api/componentes/contenedores');
    svg.selectAll("rect.contenedor")
        .data(contenedores)
        .enter().append("rect")
        .attr("class", "contenedor")
        .attr("x", (d, i) => 200 + i * 100 - 25)  // Ajustar según sea necesario
        .attr("y", 300)
        .attr("width", 50)
        .attr("height", 200)
        .style("fill", "lightgrey");
}

// Función para animar la caída de las bolas
function caerBola(valorDistribucion) {
    const x = 400 + valorDistribucion * 100; // Ajustar según sea necesario
    svg.append("circle")
        .attr("cx", 400)
        .attr("cy", 0)
        .attr("r", 5)
        .style("fill", "red")
        .transition()
        .duration(2000)
        .attr("cx", x)
        .attr("cy", 300);
}

// Conectar al backend para recibir los datos en tiempo real
const eventSource = new EventSource("/api/visualizacion/stream");
eventSource.onmessage = function(event) {
    const valorDistribucion = parseFloat(event.data);
    caerBola(valorDistribucion);
};

// Dibujar la máquina de Galton
dibujarClavos();
dibujarContenedores();

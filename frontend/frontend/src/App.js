import * as d3 from "d3";
import React, { useEffect } from "react";

const App = () => {
  useEffect(() => {
    const svg = d3.select("svg");
    const width = +svg.attr("width");
    const height = +svg.attr("height");

    // Function to fetch data from the backend
    async function obtenerDatos(url) {
      const response = await fetch(url);
      return await response.json();
    }

    // Function to draw the nails
    async function dibujarClavos() {
      const clavos = await obtenerDatos('/api/componentes/clavos');
      svg.selectAll("circle.clavo")
          .data(clavos)
          .enter().append("circle")
          .attr("class", "clavo")
          .attr("cx", (d, i) => 200 + (i % 5) * 100)  // Adjust as needed
          .attr("cy", (d, i) => 50 + Math.floor(i / 5) * 50)  // Adjust as needed
          .attr("r", 5)
          .style("fill", "black");
    }

    // Function to draw the containers
    async function dibujarContenedores() {
      const contenedores = await obtenerDatos('/api/componentes/contenedores');
      svg.selectAll("rect.contenedor")
          .data(contenedores)
          .enter().append("rect")
          .attr("class", "contenedor")
          .attr("x", (d, i) => 200 + i * 100 - 25)  // Adjust as needed
          .attr("y", 300)
          .attr("width", 50)
          .attr("height", 200)
          .style("fill", "lightgrey");
    }

    // Function to animate the falling balls
    function caerBola(valorDistribucion) {
      const x = 400 + valorDistribucion * 100; // Adjust as needed
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

    // Connect to the backend to receive real-time data
    const eventSource = new EventSource("/api/visualizacion/stream");
    eventSource.onmessage = function(event) {
      const valorDistribucion = parseFloat(event.data);
      caerBola(valorDistribucion);
    };

    // Draw the Galton machine
    dibujarClavos();
    dibujarContenedores();
  }, []);

  return <svg width="800" height="600"></svg>;
};

export default App;
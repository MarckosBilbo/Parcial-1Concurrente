import engine from "matter-js";
import Matter from 'matter-js';

document.addEventListener('DOMContentLoaded', () => {
  const { Engine, Render, Runner, Bodies, Composite, Body, Events } = Matter;

  const engine = Engine.create();
  const world = engine.world;

  // Aumentar la gravedad
  engine.gravity.y = 1.5;

  const render = Render.create({
    element: document.getElementById('galton-board'),
    engine: engine,
    options: {
      width: 400,
      height: 600,
      wireframes: false,
      background: '#fff'
    }
  });

  Render.run(render);
  const runner = Runner.create();
  Runner.run(runner, engine);

  const rows = 25;
  const cols = 25;
  const pinSpacing = 15;

  let ballStartX = 275; // Variable para almacenar la posición x del clavo de la cima

  for (let row = 0; row < rows; row++) {
    for (let col = 0; col <= row; col++) {
      const xOffset = (rows - row) * pinSpacing / 2;
      if (row === 0 && col === 0) {
        ballStartX = col * pinSpacing + xOffset; // Guardar la posición x del clavo de la cima
        continue; // Omitir el clavo de la cima
      }
      const pin = Bodies.circle(col * pinSpacing + xOffset, row * pinSpacing + 15, 3, { isStatic: true });
      Composite.add(world, pin);
    }
  }

  const bins = [];
  const binSpacing = 50; // Ajustar el espaciado entre los contenedores para cubrir toda la parte baja del tablero
  for (let i = 0; i <= 8; i++) { // Reducir el número de contenedores a 8
    const bin = Bodies.rectangle(i * binSpacing + (400 - binSpacing * 8) / 2, 580, 10, 40, { isStatic: true });
    bins.push(bin);
    Composite.add(world, bin);
  }

  // Ajustar las posiciones de las paredes diagonales para que estén más cerca de los clavos
  const leftWall = Bodies.rectangle(200 - (rows * pinSpacing / 2) - 1, 345, 2, 725, {
    isStatic: true,
    angle: Math.PI * 207 / 180 // 208 grados en radianes
  });
  const rightWall = Bodies.rectangle(200 + (rows * pinSpacing / 2) + 1, 395, 2, 820, {
    isStatic: true,
    angle: -Math.PI * -333 / 180 // 335 grados en radianes
  });

  Composite.add(world, [leftWall, rightWall]);

  // Reducir el número de bolas a la mitad
  const ballCount = 300;
  let ballsFallenCount = 0;

  for (let i = 0; i < ballCount; i++) {
    setTimeout(() => {
      const ball = Bodies.circle(ballStartX, -10, 4, { restitution: 0.4 }); // Aumentar el rebote y ajustar la posición inicial
      Body.setVelocity(ball, { x: 0, y: 0.1 }); // Reducir la velocidad inicial
      Composite.add(world, ball);
    }, i * 100); // Aumentar el intervalo entre bolas
  }

  Events.on(engine, 'afterUpdate', () => {
    bins.forEach(bin => {
      const ballsInBin = Composite.allBodies(world).filter(body => {
        return body.position.x > bin.position.x - 5 && body.position.x < bin.position.x + 5 && body.position.y > 560;
      });
      Body.setPosition(bin, { x: bin.position.x, y: 580 - ballsInBin.length * 5 });
    });

    ballsFallenCount++;
    if (ballsFallenCount >= ballCount) {
      // Dibujar una línea de puntos roja que conecta los cabezales de los contenedores
      const context = render.context;
      context.beginPath();
      context.setLineDash([5, 5]); // Línea de puntos
      context.moveTo(bins.position.x, bins.position.y - 20); // Ajustar la posición y altura del cabezal del contenedor
      for (let i = 1; i < bins.length; i++) {
        context.lineTo(bins[i].position.x, bins[i].position.y - 20); // Ajustar la posición y altura del cabezal del contenedor
      }
      context.strokeStyle = 'rgba(255, 0, 0, 1)'; // Color rojo
      context.lineWidth = 2;
      context.stroke();

      // Dibujar los tres contenedores centrales en rojo
      for (let i = 3; i <= 5; i++) {
        context.fillStyle = 'rgba(255, 0, 0, 1)'; // Color rojo
        context.fillRect(bins[i].position.x - 5, bins[i].position.y - 20, 10, 40); // Dibujar el contenedor en rojo
      }
    }
  });

  Events.on(engine, 'beforeUpdate', () => {
    Composite.allBodies(world).forEach(body => {
      if (body.label === 'ball') {
        const direction = body.position.x < 200 ? 205 : -200; // Aumentar la fuerza hacia el centro
        Body.applyForce(body, body.position, { x: direction, y: 0 });
      }
    });
  });
});

export default {
  engine: Matter.Engine.create(),
  render: Matter.Render.create(),
  createBall: () => {
    const ball = Matter.Bodies.circle(200, 0, 4);
    Matter.Composite.add(engine.world, ball);
  }
};




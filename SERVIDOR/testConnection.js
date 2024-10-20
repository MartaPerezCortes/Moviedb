const express = require('express');
const { Pool } = require('pg');

const pool = new Pool({
  user: 'postgre',
  host: 'postgre.c0slroodj8gg.us-east-1.rds.amazonaws.com',
  database: 'postgres',
  password: 'Sanvalero2024',
  port: 5432,
  ssl: {
    require: true,
    rejectUnauthorized: false
  }
});
const app = express();

// Middleware para parsear JSON en el cuerpo de las peticiones
app.use(express.json());

// Define un puerto para el servidor
const port = 3000;
pool.connect();

(async () => {
  try {
    const client = await pool.connect();
    console.log("Conexión exitosa a la base de datos");
    client.release();
  } catch (err) {
    console.error("Error de conexión:", err);
  }
 
})();

app.get('/peliculas', async (req, res) => {
    try {
        const result = await pool.query('SELECT * FROM peliculas');
        res.json(result.rows);
    } catch (err) {
        console.error(err);
        res.status(500).send('Error al obtener las películas');
    }
});
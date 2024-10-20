const express = require('express');
const cors = require('cors');
const pool = require('./config/db'); // Asegúrate de que apunte correctamente al archivo donde configuras la base de datos

const app = express();
const PORT = 4000;

// Middlewares
app.use(cors());
app.use(express.json());

// Ruta para verificar que el servidor está en funcionamiento
app.get('/', (req, res) => {
  res.send('Servidor funcionando correctamente');
});

// Ruta para obtener todos los usuarios
app.get('/usuarios', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM usuarios');
    res.json(result.rows);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al obtener los usuarios' });
  }
});

app.get('/login', async (req, res) => {
  console.log("Llegó una solicitud a /login");
    const { EMAIL, PASSWORD } = req.query;
    
    console.log("Valores recibidos para consulta:", EMAIL, PASSWORD);


    try {
        if (!EMAIL || !PASSWORD) {
            return res.status(400).send('Faltan parámetros: EMAIL y PASSWORD');
        }

        const result = await pool.query('SELECT * FROM usuarios WHERE email = $1 AND password = $2', [EMAIL, PASSWORD]);

        if (result.rows.length > 0) {
            res.json(result.rows[0]);  // Devolver una lista de usuarios
        } else {
            res.status(401).send('Usuario no encontrado o credenciales incorrectas');
        }
    } catch (err) {
        console.error(err);
        res.status(500).send('Error en el servidor');
    }
});


// Ruta para obtener todas las películas
app.get('/peliculas', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM peliculas');
    res.json(result.rows);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al obtener las películas' });
  }
});

// Ruta para crear un nuevo usuario
app.post('/usuarios/registro', async (req, res) => {
  const { email, password } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO usuarios (email, password) VALUES ($1, $2) RETURNING *',
      [email, password]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al crear el usuario' });
  }
});

// Ruta para crear una nueva película
app.post('/peliculas/nueva', async (req, res) => {
  const { titulo, descripcion, director, anyo, url_imagen } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO peliculas (titulo, descripcion, director, anyo, url_imagen) VALUES ($1, $2, $3, $4, $5) RETURNING *',
      [titulo, descripcion, director, anyo, url_imagen]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al crear la película' });
  }
});

// Iniciar el servidor
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});

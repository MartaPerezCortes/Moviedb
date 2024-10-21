const express=require('express');
const cors=require('cors');
const pool=require('./config/db');

const app=express();
const PORT=3000;

app.use(cors());
app.use(express.json());

app.get('/', (req, res) =>{
    res.send('Servidor funcionando');
});

app.get('/login', async (req,res)=>{
    const {EMAIL, PASSWORD}= req.query;

    try{

        if(!EMAIL||!PASSWORD){
            return res.status(400).send('Complete email y password');
        }
        const result= await pool.query('SELECT * FROM usuarios WHERE email=$1 AND PASSWORD=$2', [EMAIL, PASSWORD]);

        if(result.rows.length>0){
            res.json(result.rows[0]);
        }else{
            res.status(401).send('Usuario no encontrado')
        }
    }catch (err){
        console.error(err);
        res.status(500).send('Error en servidor');
    }
});

app.listen(PORT, () => {
    console.log(`Servidor ejecutándose en http://localhost:${PORT}`);
});
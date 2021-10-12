const mongoose = require('mongoose');

const ReportsSchema = new mongoose.Schema({
    idUsuario : {type: String},
    categoria : {type: String, default: 'other'},
    foto : {type:String},
    fechaReporte:{type: Date},
    ubicacion : {type:String},
    descripcion : {type:String},
    estado : {type:String}
});

// 'reports' is the name of the collection
module.exports = new mongoose.model('reports', ReportsSchema);
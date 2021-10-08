const mongoose = require('mongoose');

const ReportsSchema = new mongoose.Schema({
    idUsuario : {type: String, required: true},
    categoria : {type: String, required: true, default: 'other'},
    foto : {type:String, required:true},
    fechaReporte:{type:Date, required:false},
    ubicacion : {type:String, required:true},
    descripcion : {type:String, required:true},
    estado : {type:String, required:false}
});

// 'reports' is the name of the collection
module.exports = new mongoose.model('reports', ReportsSchema);
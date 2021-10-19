const mongoose = require('mongoose');

const ReportsSchema = new mongoose.Schema({
    id : {type:String},
    idUsuario : {type: String, require: true},
    categoria : {type: String, default: 'Otros'},
    foto : {type:String, default: "null"},
    fechaReporte:{type: Date, require: true },
    ubicacion : {type:String, require: true},
    descripcion : {type:String, require: true},
    estado : {type:String, require: true}
});

// 'reports' is the name of the collection
module.exports = new mongoose.model('reports', ReportsSchema);
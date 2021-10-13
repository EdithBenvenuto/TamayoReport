var appRoot = require('app-root-path');
const { reportsPhotoFolder } = require('../config');
const fs = require('fs');
const path = require('path');
const mongoose = require('mongoose');
const ReportModel = require('../models/Report');

// Now this service is providing whatever is needed to interact with the database but at the same time
// validating the BLL requirements.

const createReport = async (foto, fechaReporte, categoria,ubicacion,descripcion,id, idUser) =>{
    const report =new ReportModel({
        id:id,
        idUsuario : id,
        fechaReporte:fechaReporte,
        categoria: categoria,
        ubicacion: ubicacion,
        descripcion : descripcion,
        estado : "Recibido"
    });
    
    //if(foto) report.foto = foto.path;
    if(foto) report.foto = foto.filename;
    const newReport = await report.save();
    console.log("nuevo Reporte = ", report);
    return newReport;
}

const getReport = async (id) =>{
    const report = await ReportModel.findById(id);
    return report;
}
const getUserReports = async(id) =>{
    const reports = await ReportModel.find({idUsuario : id});
    return reports;
}

const getAllReports = async () =>{
    const reports = await ReportModel.find({});
    return reports;
}

const updateReport = async (id,fechaReporte,estado) =>{
    const report = await ReportModel.findById(id);
    report.fechaReporte= fechaReporte;
    report.estado = estado;
    await report.save();
    return report;
}

function deletePhoto(photoPath) {
    let fullPath = path.join(appRoot + `/${reportsPhotoFolder}/` + photoPath)
    fs.unlink(fullPath, (err) => {
        if (err) {
            console.error(`Error when deleting photo from fs ${err.message}`)
        } else {
            console.log(`Photo ${photoPath} deleted successfully`);
        }
    })
}

const deleteReport = async (id) => {
    const report = await ReportModel.findOneAndDelete({ _id: id });
    if (report.foto) {
        deletePhoto(report.foto);
    }
    return report;
}

module.exports = {
    createReport,
    getReport,
    getAllReports,
    updateReport,
    deleteReport,
    getUserReports
};
var appRoot = require('app-root-path');
const { reportsPhotoFolder } = require('../config');
const fs = require('fs');
const path = require('path');
const ReportModel = require('../models/Report');

// Now this service is providing whatever is needed to interact with the database but at the same time
// validating the BLL requirements.

const createReport = async (foto,ubicacion,descripcion,estado) =>{
    const report =new ReportModel({
        fechaReporte=Date.now(),
        ubicacion = ubicacion,
        descripcion = descripcion,
        estado = "Recibido"
    });

    if(foto) report.foto = foto.filename;

    const newReport = await report.save();

    return newReport;
}

const getReport = async (id) =>{
    const report = await ReportModel.findById(id);
    return report;
}

const getAllReports = async () =>{
    const reports = await ReportModel.find({});
    return reports;
}

const updateReport = async (id,foto,ubicacion,descripcion, keepPhoto) =>{
    const report = await ReportModel.findById(id);
    report.fechaReporte= Date.now();
    report.ubicacion=ubicacion;
    report.descripcion=descripcion;

    const previousPicture = report.foto
    let removePhoto = false;

    if (foto) {
        // User uploaded an image, this will overwrite the previous image
        report.foto = foto.filename;
    } else {
        // User did not upload an image, here it might mean to drop the image, keepPhoto will tell
        if (!keepPhoto) {
            report.foto = null;
            removePhoto = true;
        }
    }

    await report.save();
    if (removePhoto) deletePhoto(previousPicture)

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
    deleteReport
};
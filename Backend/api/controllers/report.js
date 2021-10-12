const ReportService = require('../../services/report');

// Now the controller is using the services associated to the report resource.
// Here, we use the req,res, extracting whatever the service needs to work.

module.exports = {

    createReport: async(req, res, next) =>{
        console.log("req body",req.body);
        //let {id,category,ubication,description} = req.body.product;
        let id = req.body.product._id;
        let category = req.body.product.category;
        let ubication = req.body.product.ubication;
        let description = req.body.product.description;
        let fechaReporte = Date.now();
        console.log("ATRIBUTOS = ", req.body.product.category);
        try{
            const report = await ReportService.createReport(req.foto,fechaReporte,category,ubication,description,id);
            res.status(201).json(report); //201 = created
            
        }catch (err){
            res.status(500).json({ "message": `error: ${err.message}` });
            console.log(err.message);
        }
    },

    getReport: async (req,res, next) =>{
        const reportId = req.params.id;
        try {
            const report = await ReportService.getReport(reportId);
            if (report) {
                res.json(report);
            } else {
                res.status(404).json({ "message": "NotFound" }); // 404: Not found
            }
        } catch (err) {
            res.status(500).json({ "message": `Request for id ${reportId} caused an error` });
            console.log(err.message);
        }
    },
    getUserReports: async (req,res, next) =>{
        const userId = req.params.id;
        try {
            const reports = await ReportService.getUserReports(userId);
            if (reports) {
                res.json(reports);
            } else {
                res.status(404).json({ "message": "NotFound" }); // 404: Not found
            }
        } catch (err) {
            res.status(500).json({ "message": `Request for id ${userId} caused an error` });
            console.log(err.message);
        }
    },

    getAllReports: async (req, res, next) => {
        try {
            const reports = await ReportService.getAllReports();
            res.json(reports);
        } catch (err) {
            res.status(500).end(`Request for all reports caused an error`);
            console.log(err.message);
        }
    },

    updateReport: async (req, res, next) => {
        const reportId = req.params.id;
        const {id,category,ubication,description,state} = req.body.report;
        let fechaReporte=Date.now;
        const { keepPhoto } = req.body;
        try {
            const report = await ReportService.getReport(reportId);
            if (report) {
                const updateReport = await ReportService.updateReport(reportId, req.foto,fechaReporte,category,ubication, description,state, keepPhoto,id);
                res.json(updateReport);
            } else {
                res.status(404).json({ "message": `Report with id ${reportId} does not exist` });
                console.log(`Report with id ${reportId} does not exist`);
            }
        } catch (err) {
            res.status(500).end(`Request for updating reportId ${reportId} caused an error ${err.message}`);
        }
    },

    deleteReport: async (req, res, next) => {
        const reportId = req.params.id;
        try {
            const report = await ReportService.deleteReport(reportId);
            res.json(report);
        } catch (err) {
            res.status(500).json({ "message": `Request for deleting reportId ${reportId} caused an error` });
            console.log(err.message);
        }
    },

    getReportImage: async (req, res, next) => {
        // ! This is a violation for our controller scope, as we are accessing to file system right here.
        // ! It is possible to do it in the service, reading the file as bytes and constructing the content-type.
        // ! For didactic purposes, we can do it right here, but we know this should be in the service file
        let photoPath = req.params.foto;
        const path = require('path');
        var appRoot = require('app-root-path');
        const { reportsPhotoFolder } = require('../../config');
        let fullPath = path.join(appRoot + `/${reportsPhotoFolder}/` + photoPath);
        res.sendFile(fullPath);
    }

};
//Lee de manera automatica el archivo .env, donde se guardan los parametros del sistema, el secret, puerto, url, etc
const dotenv = require('dotenv');

dotenv.config();

module.exports = {
    port: process.env.PORT,
    mongoUrl: process.env.MONGO_URL,
    reportsPhotoFolder: process.env.REPORTS_PHOTO_FOLDER,
    secret: process.env.SECRET
};
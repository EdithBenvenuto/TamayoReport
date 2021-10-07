/***
 * This file connects with all possible routes handling resources in our server.
 * More routes could be added following the same idea.
 * Here, each resource is inside the routes folder.
 */
const express = require('express');
const router = express.Router();

router.use('/reports', require('./routes/reports'));

router.use('/users', require('./routes/users'));

module.exports = router;
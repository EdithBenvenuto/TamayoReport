const express = require('express');
const router = express.Router();
const { requireLogin } = require('../middlewares/authentication');

// We use the corresponding controller here to handle the product resource
// Now the routes are simply doing that: re-rerouting the request (including all of their context) to the corresponding controller.
const UserController = require('../controllers/user');

// Login
router.put("/login", UserController.login);
// Logout
router.put("/logout", UserController.logout);
// Create
router.post("/", UserController.createUser);
// Read one
router.get('/:id', requireLogin, UserController.getUser);
// Update user
router.put('/:id', requireLogin, UserController.updateUser);
// Delete user
router.delete('/:id', requireLogin, UserController.deleteUser);
module.exports = router;
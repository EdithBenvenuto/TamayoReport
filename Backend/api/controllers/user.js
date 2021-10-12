const UserService = require('../../services/user');
const mongoose = require('mongoose');
const {generateToken} = require('../middlewares/authentication')

// Now the controller is using the services associated to the report resource.
// Here, we use the req,res, extracting whatever the service needs to work.

module.exports = {
    createUser: async(req,res,next) =>{
        const online = true;
        const admin ={adminType: 'usuario', admin: false};
        let {name,email,password} = req.body; // JSON.parse(req.body.user);
        try{
            const user = await UserService.createUser(name,email,password,admin,online);
            res.status(201).json(user);
        }catch (err){
            res.status(500).json({ "message": `error: ${err.message}` });
            console.log(err.message);
        }
    },
    //VERIFICAR JSON.PARSE(.USER) Y RES.JSON
    login: async(req,res,next) =>{
        let {email,password} = req.body;
        
        try{
            const loginUser = await UserService.login(email,password);
            if(loginUser){
                const accessToken = generateToken(loginUser);
                res.status(200).json({ token: accessToken, userId: loginUser._id});
            }else{
                res.status(401).json({ "message": "Wrong Credentials" });
                console.log(`Invalid credentials ${email}:${password}`);
            }
        }catch (err){
            res.status(500).json({ "message": `error: ${err.message}` });
            console.log(err.message);
        }
    },
    
    logout: async(req,res,next) =>{
        const userId = req.params.id;
        try{
            const user = await UserService.getUser(userId);
            if(user){
                const userLogout = await UserService.logout(user.id);
                res.json(userLogout);
                
            }else{
                res.status(404).json({ "message": `User with id ${reportId} does not exist` });
                console.log(`User with id ${reportId} does not exist`);
            }
        } catch (err){
            res.status(500).end(`Request for updating online status ${reportId} caused an error ${err.message}`);
        }
    },
    getUser: async (req, res, next) => {
        const userId = req.params.id;
        try {
            const user = await UserService.getUser(userId);
            if (user) {
                res.json(user);
            } else {
                res.status(404).json({ "message": "NotFound" }); // 404: Not found
            }
        } catch (err) {
            res.status(500).json({ "message": `Request for id ${userId} caused an error` });
            console.log(err.message);
        }
    },

    updateUser: async (req, res, next) => {
        const userId = req.params.id;
        const online = false;
        const admin ={adminType: 'usuario', admin: false};
        const { name, email, password } = req.body;
        try {
            const user = await UserService.getUser(userId);
            if (user) {
                const updatedUser = await UserService.updateUser(userId, name, email, password,admin,online);
                res.json(updatedUser);
            } else {
                res.status(404).json({ "message": `User with id ${userId} does not exist` });
                console.log(`User with id ${userId} does not exist`);
            }
        } catch (err) {
            res.status(500).end(`Request for updating userId ${userId} caused an error ${err.message}`);
        }
    },
    deleteUser: async(req,res,next) =>{
        const userId = req.params.id;
        try{
            const user = await UserService.getUser(userId);
            if(user){
                const deletedUser = await UserService.deleteUser(userId);
                res.json(deletedUser);
            }else{
                res.status(404).json({ "message": `User with id ${userId} does not exist` });
                console.log(`User with id ${userId} does not exist`);
            }
        }catch(err){
            res.status(500).end(`Request for deleting user with userId ${userId} caused an error ${err.message}`);
        }
    }

    
};
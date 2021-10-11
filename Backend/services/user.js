const mongoose = require('mongoose');
const UserModel = require('../models/User');
const bcrypt = require("bcryptjs");

const login = async (email, password) => {

    let user = await UserModel.findOne({ email: email });
    if (user && !user.online) {
        let success = bcrypt.compareSync(password, user.password);
        if (success === true)
            user.updateOne({$set:{online: true}});
            return user;
        }else 

    return null;
};
const logout = async(id) =>{
    let user = await UserModel.findById(id);
    user.updateOne({$set:{online:false}});
    return user;

};
const createUser = async (name, email, password, admin, online)=>{
    const user = new UserModel({
        name = name,
        email= email,
        password = password,
        admin = admin,
        online = online  
    });
    const newUser = await user.save();
    return newUser;
}

const getUser = async(id)=>{
    const user = await UserModel.findById(id);
    return user;
}
const updateUser = async(id,name,email,password,admin,online) =>{
    const user = await UserModel.findById(id);
    user.name = name;
    user.email = email;
    user.password = password;
    user.admin = admin;
    user.online = online;

    await user.updateOne();
    return user;
}

const deleteUser = async(id) =>{
    const user = await UserModel.findOneAndDlete({_id:id});
    return user;

}
module.exports = {
    login,
    logout,
    createUser,
    getUser,
    updateUser,
    deleteUser
};

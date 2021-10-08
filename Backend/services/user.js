const mongoose = require('mongoose');
const UserModel = require('../models/User');
const bcrypt = require("bcryptjs");

const login = async (email, password) => {
    // const user = await UserModel.findOne({ email: email, password: password });
    // if (user) return user;
    // return null;

    let user = await UserModel.findOne({ email: email });
    if (user) {
        let success = bcrypt.compareSync(password, user.password);
        if (success === true)
            return user;
        else
            return null;
    }
    return null;
};
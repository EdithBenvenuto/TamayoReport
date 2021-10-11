const { isEmail } = require('validator');
const bcrypt = require("bcryptjs");
const mongoose = require('mongoose');

const UsersSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true,
        unique: true,
        validate: [isEmail, 'Invalid email']
    },
    password: {
        type: String,
        required: true
    },
    admin:{
        adminType :{type: String, required: false, default: 'usuario'},
        admin:{type: Boolean, required: true, default: false}
    },
    //helps verify weather user is online or offline.
    online:{
        type: Boolean,
        required: false,
        default: false
    }
});

UsersSchema.pre("save", function (next) {
    const user = this

    if (this.isModified("password") || this.isNew) {
        bcrypt.genSalt(10, function (saltError, salt) {
            if (saltError) {
                return next(saltError)
            } else {
                bcrypt.hash(user.password, salt, function (hashError, hash) {
                    if (hashError) {
                        return next(hashError)
                    }

                    user.password = hash
                    next()
                })
            }
        })
    } else {
        return next()
    }
});

module.exports = mongoose.model('users', UsersSchema);
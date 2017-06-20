function asyncFun(a, b) {
    return new Promise(function (resolve, reject) {
        if (typeof a !== 'number' || typeof b !== 'number') {
            reject(new Error('no number!'));
        }
        setTimeout(function () {
            resolve(a + b);
        }, 200)
    })
}

/**
 * Promise All
 * @type {Promise.<*[]>}
 */
var promise=Promise.all([asyncFun(1,2),asyncFun(3,5),asyncFun(7,9)]);
promise.then(function (result) {
    console.log(result);
});
/**
 * Promise Race
 * @type {Promise.<*[]>}
 */
var promise2=Promise.race([asyncFun(1,2),asyncFun(3,5),asyncFun(7,9)]);
promise2.then(function (result) {
    console.log(result);
}).catch(function (error) {
    console.log(error);
});
/**
 * promise test
 */
asyncFun(1, 2).then(function (result) {
    if (result > 2) {
        console.log(result);
        return asyncFun(result, 2);
    }
}).then(function (result) {
    if (result > 4) {
        console.log(result);
        console.log("ok");
    }
}).catch(function (error) {
    console.log(error);
});
console.log("start!");

/**
 * promise class test
 */
class User {
    constructor(name,password) {
        this.name = name;
        this.password=password;
    }

    validateName() {
        let name = this.name;
        return new Promise(function (resolve, reject) {
            if (name == 'leo') {
                resolve("username success");
            } else {
                reject('username error!');
            }
        })
    }
    validatePassword(){
        let password=this.password;
        return new Promise(function (resolve,reject) {
            if(password=='123'){
                resolve('password success');
            }else{
                reject('password error!');
            }
        })
    }
}

var user = new User('leo','123');
user.validateName().then(function (success) {
    console.log(success);
    return user.validatePassword();
}).then(function(success){
    console.log(success);
}).catch(function (error) {
    console.log(error);
});
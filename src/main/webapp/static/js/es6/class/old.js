/**
 * old class
 */
function User(name,age){
    this.name=name;
    this.age=age;
};
/**
 *static Method
 */
User.getClassName=function () {
    return 'user';
};
/**
 *Method
 */
User.prototype.changeName=function(name){
    this.name=name;
}
User.prototype.changeAge=function(age){
    this.age=age
}
Object.defineProperty(User.prototype,"info",{
    get(){
        return 'name:'+this.name+'|age:'+this.age;
    }
})
var user=new User('leo',22);
console.log(user);
user.changeAge("89");
user.changeName("tom");
console.log(user);
console.log(user.info)
console.log(User.getClassName());
/**
 *extends
 */
function Manager(name,age,password){
    User.call(this,name,age);
    this.password=password;
}
/**
 * extends static Method
 */
Manager.__proto__=User;
/**
 *extends prototype Method
 */
Manager.prototype=User.prototype;
/**
 *add new Method
 */
Manager.prototype.changePassword=function(password){
    this.password=password;
};
var manager=new Manager('amy',18,'123');
console.log(manager);
manager.changeName('marry');
manager.changePassword('345');
console.log(manager.info);
console.log(manager);
console.log(Manager.getClassName());

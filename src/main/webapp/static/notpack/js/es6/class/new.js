class User{
    constructor(name,age){
        this.name=name;
        this.age=age;
    }
    /**
     * static Method
     */
    static getClassName(){
        return "user";
    }

    /**
     * Method
     */
    changeName(name){
        this.name=name;
    }
    changeAge(age){
        this.age=age;
    }
    get info(){
        return "name:"+this.name+"|age:"+this.age;
    }
}
class Manager extends  User{
    constructor(name,age,password){
        super(name,age);
        this.password=password;
    }
    changePassword(password){
        this.password=password;
    }
}
var user=new User("jack",18);
console.log(user);
user.changeName("jackson");
user.changeAge(22);
console.log(user);
console.log(user.info);
console.log(User.getClassName());
var manager=new Manager("alis",18,'123');
console.log(manager);
manager.changePassword('456');
manager.changeName("赵丽颖");
console.log(manager);
console.log(manager.info);
console.log(Manager.getClassName());
/**
 * 立即执行
 */
let muster=new class Muster{
    constructor(name,age){
        this.name=name;
        this.age=age;
    }
}('tom',21);
console.log(muster);



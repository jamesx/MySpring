/**基本用法
 */
var test2=(name,age)=> {return {name,age}};
console.log(test2('jack',19));
/**箭头函数this
 */
class User{
    constructor(name,age){
        this.name=name;
        this.age=age;
    }
    changeName(name){
        this.name=name;
    }
    changeAge(age){
        this.age=age;
    }
    change(name,age){
        let fn=()=>{
            this.changeName(name);
            this.changeAge(age);
        }
        fn();
    }
}
var user=new User('tom',18);
user.change('jack',22);
console.log(user);
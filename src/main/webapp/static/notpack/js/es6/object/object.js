var obj={
    getName(firstName,lastName){
        //相当于 {firstName:firstName,lastName:lastName}
        return {firstName,lastName}
    },
    get name(){
        return this.name;
    },
    set name(name){
        this.name=name;
    }
}
console.log(obj.getName("tom","jack"));

/**
 *遍历对象的属性
 * @type {Symbol}
 */
var getAge=Symbol('getAge1');
var address=Symbol('address');
class A{
    constructor(name){
        this.name=name;
    }
    getName(){
        return this.name;
    }
}
class B extends A{
    constructor(name, age){
        super(name);
        this.age=age;

    }
    [getAge](){
        return this.age;
    }
}
var b=new B("tom",18);
console.log(Object.keys(b));
console.log(Object.getOwnPropertyNames(b));
console.log(Object.keys(B.prototype));
console.log(Object.getOwnPropertyNames(B.prototype));
console.log(Object.getOwnPropertySymbols(B.prototype));
for(let k in b){
    console.log(k);
}
/**
 * Symbol Test
 */
let getName = Symbol('name1');
let getAge=Symbol('age2');
let password=Symbol('password3');
var obj = {
    [getName]() {
        return "jack";
    },
    [getAge](){
        return 18;
    },
    [password]:'123'
}
console.log(obj[getName]);
var symbols=Object.getOwnPropertySymbols(obj)
console.log(symbols);
console.log(obj[symbols[0]]);
console.log(obj[symbols[1]]);
console.log(obj[symbols[2]]);
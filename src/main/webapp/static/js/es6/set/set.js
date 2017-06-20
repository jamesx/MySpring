/**
 * example1
 * @type {[*]}
 */
let arr=['1','2','3','5','5'];
let s=new Set(arr);
console.log(s);
/**
 * example2
 */
let user={name:"leo",age:18};
let s2=new Set();
s2.add(user);
s2.add(22);
console.log(s2);
s2.delete(user);
console.log(s2);
console.log(s2.has(22));
console.log(s2.size);
s2.clear();
console.log(s2);
/**
 * example2
 */
let arr2=['1','3','5','5'];
let arr3=Array.from(new Set(arr2));
console.log(arr3);
/**
 * example3
 */
let set2=new Set(arr2);
for(let a of set2){
    console.log(a);
}
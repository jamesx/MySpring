/**
 *变量的结构赋值
 */
var arr=[1,2,3];
var [a,b,c]=arr;
console.log(a+b+c);
/**
 * 多维数组的结构赋值
 */
let arr=[22,[5,8],11];
let[a,[b,c],d]=arr;
console.log(a+b+c+d);
/**
 * 变量交换
 */
let x=11;
let y=22;
[x,y]=[y,x];
console.log("x:"+x);
console.log("y:"+y);
/**
 * 不完全解析
 */
let arr=[10,[15,12],22];
let[a,[,b],c]=arr;
console.log(a+b+c);
/**
 * set结构赋值
 */
let[x,y]=new Set([11,12]);
console.log(x+y);
/**
 * 对象的结构赋值
 */
let{name}={name:"tom"};
console.log({name});
/**
 *函数默认值
 */
function test(x=12,y=15) {
    console.log(x+y);
}
test();
test(17,19);
/**
 *函数数组参数的默认值
 */
function test2([x=1,y=2]=[]){
    console.log(x,y);
}
test2();
test2([7,8]);
/**
 * 函数对象参数的结构赋值
 */
function test3({x,y}={x:12,y:18}){
    console.log(x,y);
}
test3();
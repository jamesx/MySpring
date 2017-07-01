let arr=['a','b','c'];
let arr2={
    "name":'tom',
    "age":18,
    "address":"beijing"
}
/**zhi
 * for of 遍历属性值
 */
for(let a of arr){
    console.log(a);
}
for(let a in arr2){
    console.log(arr2[a]);
}
/**
 * for in 遍历属性名或下标
 */
for(let a in arr){
    console.log(a);
}
for(let a in arr2){
    console.log(a);
}
/**
 * iterator for in 遍历属性值 example1
 */
class List{
    constructor(user){
        /**
         * user:{'leo':'110','tom':'332'}
         *
         */
        this.user=user;
    }
    [Symbol.iterator](){
        let self=this;
        let i=0;
        const names=Object.keys(this.user);
        const length=names.length;
        //iterator
        return{
            next:function () {
                let name=names[i++];
                let qq=self.user[name];
                return {value:{name,qq},done:i>length}
            }
        }
    }
}
let list = new List({'leo':'12345','tom':'67890'});
for(let user of list){
    console.log(user);
}

/**
 * example2
 */
var obj={'elise':'7878788','tony':'67890'}
let iterator=function(){
    let self=this;
    let i=0;
    const names=Object.keys(this);
    const length=names.length;
    //iterator
    return{
        next:function () {
            let name=names[i++];
            let qq=self[name];
            return {value:{name,qq},done:i>length}
        }
    }
}
obj[Symbol.iterator]=iterator;
for(let x of obj){
    console.log(x);
}
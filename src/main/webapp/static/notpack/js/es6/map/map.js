var obj={
    name:'leo',
    age:18,
    'full name':'leo jack'
}
var map=new Map();
map.set(obj,'very good!');
map.set(12,'m=12');
console.log(map);
console.log(map.get(obj));
console.log(map.get(12));
console.log(map.size);
map.delete(12);
console.log(map);
console.log(map.has(obj));
map.set(NaN,"what!");
map.set(NaN,'what two!');
console.log(map);
map.clear();
/**
 * example2
 */
let map2=new Map(
    [['name','leo'],['qq','1429090033']]
);
console.log(map2);
console.log(map2.get('name'));
console.log(map2.get('qq'));
let keys=map2.keys();
let values=map2.values();
for(let k of keys){
    console.log(k);
}
for(let v of values){
    console.log(v);
}
let entries=map2.entries();
for(let e of entries){
    console.log(e);
}
var me={
    name:'jack'
}
map2.forEach(function(k,y){
    console.log(this.name,k,y);
},me)
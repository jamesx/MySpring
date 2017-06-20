function asyncFun(a,b,c){
    setTimeout(function(){
        c(a+b);
    },200);
}
asyncFun(1,2,function(result){
    if(result>2){
        console.log(result);
        asyncFun(result,2,function (result) {
            if(result>4){
                console.log(result);
                asyncFun(result,2,function (result) {
                    console.log("ok");
                })
            }
        })
    }
})
console.log("start!");
/**
 * example1
 */
function *test(){
    yield 1;
    yield 2;
    return 3;
}
let result=test();
console.log(result.next());

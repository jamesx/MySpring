let html2 = `
    <div>
        <span>Hello!</span>
    </div>
    `;
console.log(html2);
/**
 * 模板字符串中可以使用${变量或者js语句或者function()函数}
 * @type {string}
 */
var name='leo';
var age=21;
function test(){
    return "hello,baby!";
}
let html=`
    <div>
        <ul>
            <li>${name}</li>
            <li>${age<19?age+5:age}</li>
            <li>${test()}</li>
        </ul>
    </div>
    `;
console.log(html);
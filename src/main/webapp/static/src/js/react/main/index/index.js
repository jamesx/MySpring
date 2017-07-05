/**
 * {}中可以使用函数，react会将函数的return结果渲染到{}中
 */
class H1 extends React.Component{
    constructor(props){
        super(props);
        this.state={
            id:'Date'
        }
        this.getId=this.getId.bind(this);
    }
    getId() {
        return this.state.id;
    }

    render(){
        function DateToString(d){
            return [
                d.getFullYear(),d.getMonth()+1,d.getDate()
            ].join('-');
        }
        return <h1>{this.getId()+": "+DateToString(new Date())}</h1>
    }
}
/**
 * this.props.children属性测试
 * react将组件中的数据和子组件整理在children属性中
 */
class Divider extends React.Component{
    constructor(props){
        console.log("constructor...props: "+props);
        super(props);
        this.state={
            color:"red"
        }
    };
    handleClick(){
        let color;
        if(this.state.color=="green"){
            color='red'
        }else{
            color='green'
        }
        this.setState({
            color:color
        })
    }
    componentDidMount(){
       let id= ReactDOM.findDOMNode(this).id;
       console.log('componentDidMount.. id: '+id);
    }
    /**
     * componentWillReceiveProps props被父组件修改时调用,可以在该方法中更新state
     * @returns {XML}
     */
    componentWillReceiveProps(nextProps){
        var color=nextProps.color;
        if(color!=this.props.color){
            this.state.color=color;
        }
        console.log("componentWillReceiveProps.. nextProps:"+nextProps.color);
    }

    /**
     * shouldComponentUpdate 组件渲染前调用，当返回false时，不渲染
     * @returns {XML}
     */
    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate..nextProps: "+nextProps.color+" nextState: "+nextState.color);
    return true;
    }

    /**
     * componentWillUpdate 组件渲染前调用该方法，不可以在该方法中修改state和props
     */
    componentWillUpdate(nextProps, nextState){
        console.log("componentWillUpdate..nextProps: "+nextProps.color+" nextState: "+nextState.color);
    }

    /**
     * componentDidUpdate 组件渲染后调用该方法
     * @returns {XML}
     */
    componentDidUpdate(nextProps, nextState){
        console.log("componentDidUpdate..nextProps: "+nextProps.color+" nextState: "+nextState.color);
    }
    componentWillUnmount(){
        console.log("componentWillUnmount..");
    }
    render(){
        console.log('render...props: '+this.props.color+" state: "+this.state.color);
        /**
         * 定义样式，驼峰写法
         * @type {{backgroundColor: string, fontSize: string}}
         */
        let style={
            backgroundColor:'#f9ffe2',
            fontSize:'8px'
        }
            return <div className="divider" id="1">
                <h1 id="2" style={style}>{this.props.children}</h1><hr/>
                <input value={this.state.color} onChange={(e)=>this.setState({color:e.target.value})}/>
                <button onClick={()=>this.handleClick()}>Click me!</button>
            </div>

    }
}
let childrenH=[<H1 key="1"/>,<H1 key="2"/>,<H1 key="3"/>];
ReactDOM.render(<Divider color="green">{childrenH}</Divider>,document.body);
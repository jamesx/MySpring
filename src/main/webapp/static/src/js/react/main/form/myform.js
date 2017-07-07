/**
 * Created by Administrator on 2017/7/6.
 */
class MyForm extends  React.Component{
    constructor(props){
        super(props);
        this.state={
            id:"",
            name:""
        }
    }
    handleChange(name,event){
        this.setState({
            [name]:event.target.value
        })
    }
    render(){
        return <div>
            <input type="text"  value={this.state.id} onChange={(e)=>this.handleChange("id",e)}/>
            <input type="text"  value={this.state.name} onChange={(e)=>this.handleChange('name',e)}/>
        </div>
    }
}
ReactDOM.render(<MyForm/>,document.body);
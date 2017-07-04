/**
 * Created by Administrator on 2017/7/3.
 */
class Form extends React.Component{
    constructor(props){
        super(props)
        this.state={
            inputValue:'tom',
            checkboxValue:false,
            selectValue:"B"
        }
       this.changInput=this.changInput.bind(this);
        this.changeCheckbox=this.changeCheckbox.bind(this);
        this.changeSelect=this.changeSelect.bind(this)
    }
    changInput(event){
        this.setState({
            inputValue:event.target.value
        })
    }
    changeCheckbox(){
        this.setState({
            checkboxValue:!this.state.checkboxValue
        })
    }
    changeSelect(event){
        this.setState({
            selectValue: event.target.value
        })
    }
    render(){
        return <form>
            <ul>
                <li><input type="text" value={this.state.inputValue} onChange={this.changInput}/></li>
                <li><input type="checkbox" checked={this.state.checkboxValue} onChange={this.changeCheckbox} />checkbox</li>
                <li><input type="radio"/>radio</li>
                <li>
                    <select value="B" onChange={this.changeSelect}>
                        <option value="A">Tom</option>
                        <option value="B">Jack</option>
                        <option value="C">Any</option>
                        <option value="D">Li</option>
                    </select>
                </li>
                {/*<li>*/}
                    {/*<select value="B" multiple>*/}
                        {/*<option value="A">Tom</option>*/}
                        {/*<option value="B">Jack</option>*/}
                        {/*<option value="C">Any</option>*/}
                        {/*<option value="D">Li</option>*/}
                    {/*</select>*/}
                {/*</li>*/}
                <li><textarea value="textarea"></textarea></li>
            </ul>
        </form>
    }
}
export default Form;
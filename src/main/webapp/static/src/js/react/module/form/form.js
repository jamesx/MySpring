/**
 * Created by Administrator on 2017/7/3.
 */
class Form extends React.Component{
    constructor(props){
        super(props)
        this.state={
            inputValue:'tom',
            checkboxValue:1,
            selectValue:"B",
            radioValue:true,
            textareaValue:""
        }
        this.changeTextarea=this.changeTextarea.bind(this);
       this.changInput=this.changInput.bind(this);
       this.changeRadio=this.changeRadio.bind(this);
        this.changeSelect=this.changeSelect.bind(this);
        this.changeCheckbox=this.changeCheckbox.bind(this);
    }
    changInput(event){
        this.setState({
            inputValue:event.target.value
        })
    }
    changeTextarea(event){
        this.setState({
            textareaValue:event.target.value
        })
    }
    changeRadio(){
        this.setState({
        radioValue:!this.state.radioValue
        })
    }
    changeSelect(event){
        this.setState({
            selectValue: event.target.value
        });
        this.refs.pp.value=event.target.value;
    }
    changeCheckbox(event){
        this.setState({
            checkboxValue:event.target.value
        })
    }
    render(){
        let checkValue=this.state.checkboxValue;
        let check1,check2,check3;
        if(checkValue==1){
            check1=true,check2=false,check3=false;
        }else if(checkValue==2){
            check1=false,check2=true,check3=false;
        }else{
            check1=false,check2=false,check3=true;
        }
        return <form>
            <ul>
                <li><input type="text" value={this.state.inputValue} onChange={this.changInput}/></li>
                <li>
                    <input type="checkbox"  value="1" checked={check1} onChange={this.changeCheckbox}/>checkbox1
                    <input type="checkbox"  value="2" checked={check2} onChange={this.changeCheckbox}/>checkbox2
                    <input type="checkbox"  value="3" checked={check3} onChange={this.changeCheckbox}/>checkbox3
                </li>
                <li>
                    <input type="radio" name="sex" value="0" checked={this.state.radioValue}  onChange={this.changeRadio}/>男
                    <input type="radio" name="sex" value="1" checked={!this.state.radioValue} onChange={this.changeRadio}/>女
                </li>
                <li>
                    <select value={this.state.selectValue} onChange={this.changeSelect}>
                        <option value="A">Tom</option>
                        <option value="B">Jack</option>
                        <option value="C">Any</option>
                        <option value="D">Li</option>
                    </select>
                </li>
                <li><input ref="pp" /></li>
                <li><textarea value={this.state.textareaValue} onChange={this.changeTextarea}></textarea></li>
            </ul>
        </form>
    }
}
export default Form;
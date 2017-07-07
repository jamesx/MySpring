/**
 * Created by Administrator on 2017/7/6.
 */
class Radio extends React.Component{
    constructor(props){
        super(props);
        var id=props.id?props.id : 'radio-';
        this.state={
            checked:!props.checked,
            id:id,
            name:id
        }
        this.handleClick=this.handleClick.bind(this);
    }
    static defaultProps(){
        id:null;
        checked:false
    }
    handleClick(e){
        this.setState({
            checked:e.target.checked
        });
        this.props.changeRadio(this.props.value);
    }
    render(){
        return <div className="radio">
            <label htmlFor={this.props.id}>
                <input type="radio" name={this.props.name}  id={this.props.id} onChange={this.handleClick} value={this.props.name} checked={this.props.checked}/>{this.props.label}
            </label>
        </div>
    }
}
class ChoiceQuestion extends  React.Component{
    constructor(props){
        super(props);
        this.state={
            id:'choice-',
            value:props.value
        }
        this.renderChoices=this.renderChoices.bind(this);
        this.changeRadio=this.changeRadio.bind(this);
    }
    changeRadio(value){
        this.setState({
            value:value
        })
    }
    renderChoices(){
       return this.props.choices.map((choice,i)=>{
           return <Radio id={'choice-'+i} changeRadio={this.changeRadio} name={this.state.id} label={choice} value={choice} checked={this.state.value==choice}>{this.props.label}</Radio>
       })
    }
    render(){
        return <div className="form-group">
            {this.renderChoices()}
        </div>
    }
}
export default  ChoiceQuestion;

/**
 * Created by Administrator on 2017/6/29.
 */
class Item extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isEdit: true
        }
        this.onEdit = this.onEdit.bind(this);
        this.remove = this.remove.bind(this);
        this.save = this.save.bind(this);
    };

    onEdit() {
        this.setState({
            isEdit:true
        })
    }

    remove() {
        this.props.onRemove(this.props.id);
    }
    save() {
        this.props.onSave(this.props.id, this.refs.inputText.value);
        this.setState({
            isEdit:false
        })
    };

    render() {
        return this.state.isEdit ? <li className="list-group-item"><input type="text"  className="item-edit" ref="inputText"
                                                   defaultValue={this.props.value}/>
                <a href="#" className="glyphicon glyphicon-ok" onClick={this.save}></a><a href="#" onClick={this.remove}
                                                                                          className="glyphicon glyphicon-remove"></a>
            </li>
            : <li className="list-group-item">{this.props.value} <a onClick={this.remove}
                                                                                                   className="right glyphicon glyphicon-remove"
                                                                                                   href="#"></a><a
                onClick={this.onEdit} className="right glyphicon glyphicon-pencil" href="#"></a></li>
    }
}
;

export default Item;

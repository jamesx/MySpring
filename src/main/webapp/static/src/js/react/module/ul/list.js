import Item from './item';
class List extends  React.Component{
    constructor(props){
        super(props);
        this.state={
            count:0,
            listDOM:new Map(),
        }
        this.add=this.add.bind(this);
        this.removeItem=this.removeItem.bind(this);
        this.save=this.save.bind(this);
    };
    add(e){
        e.preventDefault();
        let itemList=this.state.listDOM;
        itemList.set(this.state.count+1,'');
        this.setState({
           count:this.state.count+1,
            ListDOM:itemList
        })
    };
    save(id,value){
        let itemList=this.state.listDOM;
        itemList.set(id,value);
        this.setState({
            listDOM:itemList
        })
    }
    removeItem(id){
        let itemList=this.state.listDOM;
        itemList.delete(id);
        this.setState({
            listDOM:itemList
        })
    };
    render(){
        const listDOM=[];
        for(let item of this.state.listDOM){
           listDOM.push(<Item key={item[0]} id={item[0]} onSave={this.save}  onRemove={this.removeItem} value={item[1]}> </Item>) ;
        }
        return <div>
            <button className="btn btn-default" onClick={this.add}>Add</button>
            <ul className="list-group">
                {listDOM}
            </ul>
        </div>
}
}
export default  List;

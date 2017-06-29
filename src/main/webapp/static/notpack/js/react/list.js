import React, { Component } from 'react';
import ItemEditor from './itemEditor';
import Item from './item';
class List extends  React.Component{
    constructor(props){
        super(props);
        this.state={
            count:0,
            listDOM:new Map(),
            editListDOM:new Map()
        }
        this.add=this.add.bind(this);
        this.removeEditItem=this.removeEditItem.bind(this);
        this.removeItem=this.removeItem.bind(this);
    };
    add(e){
        e.preventDefault();
        let editList=this.state.editListDOM;
        editList.set(this.state.count+1,{value:''});
        this.setState({
           count:this.state.count+1,
            editListDOM:editList
        })
    };
    removeItem(id){
        let itemList=this.state.listDOM;
        itemList.delete(id);
        this.setState({
            listDOM:itemList
        })
    };
    removeEditItem(id){
        let editList=this.state.editListDOM;
        editList.delete(id);
        this.setState({
            editListDOM:editList
        })
    }
    render(){
        const listDOM=[];
        const editListDOM=[];
        for(let item of this.state.listDOM){
           listDOM.push(<Item  id={item[0]} onRemove={this.removeItem} value="this is"> {item[1].value}</Item>) ;
        }
        for(let item of this.state.editListDOM){
            editListDOM.push(<ItemEditor key={item[0]} onRemove={this.removeEditItem} id={item[0]} value={item[1].value}/>);
        }
        return <div>
            <button className="btn btn-default" onClick={this.add}>Add</button>
            <ul className="list-group">
                {listDOM}
                {editListDOM}
            </ul>
        </div>
}
}
export default  List;

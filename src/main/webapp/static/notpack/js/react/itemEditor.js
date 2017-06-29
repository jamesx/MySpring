/**
 * Created by Administrator on 2017/6/29.
 */
import React, { Component } from 'react';
class ItemEditor extends  React.Component{
    constructor(props){
        super(props);
        this.state={
            value:""
        }
        this.remove=this.remove.bind(this);
        this.changeHandle=this.changeHandle.bind(this);
    };
    changeHandle(event) {
        this.setState({
            value:event.target.value
        })
    }
    ;
    remove(){
        this.props.onRemove(this.props.id);
    };
    render(){
        return <li className="list-group-item"><input type="text" onChange={this.changeHandle} className="item-edit" value={this.state.value}/>
                     <a href="#" className="glyphicon glyphicon-ok"></a><a href="#"  onClick={this.remove} className="glyphicon glyphicon-remove"></a>
                </li>
    }
};
export  default  ItemEditor;
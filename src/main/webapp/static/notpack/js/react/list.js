import React, { Component } from 'react';
import ItemEditor from './itemEditor';
import Item from './item';
class List extends  React.Component{
    constructor(props){
        super(props);
        this.state={

        }
    };
    render(){
        return <div>
            <button className="btn btn-default">Add</button>
            <ul className="list-group">
                <Item/>
                <ItemEditor/>
            </ul>
        </div>
}
}
export default  List;

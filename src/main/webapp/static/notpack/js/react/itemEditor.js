/**
 * Created by Administrator on 2017/6/29.
 */
import React, { Component } from 'react';
class ItemEditor extends  React.Component{
    constructor(props){
        super(props);
    };
    render(){
        return <li className="list-group-item"><input type="text" className="item-edit"/>
                     <a href="#" className="glyphicon glyphicon-ok"></a><a href="#" className="glyphicon glyphicon-remove"></a>
                </li>
    }
};
export  default  ItemEditor;
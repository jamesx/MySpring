/**
 * Created by Administrator on 2017/6/29.
 */
import React, { Component } from 'react';
class Item extends React.Component{
    constructor(props){
        super(props);
    };
    render(){
        return  <li className="list-group-item">Cras justo odio <a className="right glyphicon glyphicon-remove" href="#"></a><a className="right glyphicon glyphicon-pencil" href="#"></a></li>
    }
};
export default Item;

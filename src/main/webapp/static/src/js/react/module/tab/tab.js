/**
 * Created by Administrator on 2017/7/5.
 */
class Tab extends React.Component{
    constructor(props){
        super(props);
        const currProps=props;
        let activeIndex=0;
        if(activeIndex in currProps){
            activeIndex=currProps.activeIndex;
        }else if (defaultActiveIndex in currProps){
            activeIndex=currProps.defaultActiveIndex;
        }
        this.state={
            activeIndex,
            prevIndex:activeIndex
        }
    }
    static defaultProps={
        classPrefix:'tabs',
        onChange:()=>{}
    }
    render(){
        return <div className="ui-tabs"></div>
    }
}
export default Tab;
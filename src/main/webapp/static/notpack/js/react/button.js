class Button extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            count: 0
        }
        this.handleClick = this.handleClick.bind(this);
    }
    handleClick(e) {
        e.preventDefault();
        this.setState({
            count: this.state.count + 1
        })
    }

    render() {
        const {color, text} = this.props;
        return <div>
            <p>{this.state.count}</p>
            <button onClick={this.handleClick} className={color}>{text}</button>
        </div>
    }
}
Button.defaultProps = {
    color: 'red',
    text: 'ComeOn!'
}
ReactDOM.render(<Button/>, document.getElementById("example1"));
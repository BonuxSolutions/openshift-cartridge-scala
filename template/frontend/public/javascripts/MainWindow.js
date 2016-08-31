var ContentBox = React.createClass({
    getInitialState: function () {
        return {};
    },
    handleEventSelect: function (eventId) {

    },
    render: function () {
        return (
            <h1>
                Hello!
            </h1>
        );
    }
});

window.renderContent = function () {
    ReactDOM.render(<ContentBox />, document.getElementById("content"));
}
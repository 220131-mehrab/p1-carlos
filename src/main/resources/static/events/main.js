let searchTerms = window.location.search;

fetch('/events' + searchTerms).then(resp => resp.json()).then(events => {
    document.querySelector('#events').innerHTML = printEvents(events);
});

function printEvents(json) {
    return `${json.map(printEvent).join('\n')}`;
}

function printEvent(event) {
    return '<p>' + event.eventId + ': ' + event.eventName + '</p>';
}


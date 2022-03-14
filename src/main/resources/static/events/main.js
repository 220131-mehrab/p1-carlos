let searchTerms = window.location.search;

fetchEvents();
if (searchTerms != '')
    fetchRegistered()

function fetchEvents() {
    fetch('/events' + searchTerms).then(resp => resp.json()).then(events => {
        document.querySelector('#events').innerHTML = printEvents(events);
    });
}

function printEvents(json) {
    return `${json.map(printEvent).join('\n')}`;
}

function printEvent(event) {
    if (searchTerms == '')
        return '<a href="/events/events.html?eventId=' + event.eventId + '">' + event.eventId + ': ' + event.eventName + '</a></br>';
    else
        return '<h2>' + event.eventId + ': ' + event.eventName + '</h2></br>';
}

function fetchRegistered() {
    fetch('/registered' + searchTerms).then(resp => resp.json()).then(teams => {
        document.querySelector('#registered').innerHTML = printTeams(teams);
    })
}

function printTeams(json) {
    return `<h4>Teams Attending:</h4>
    ${json.map(printTeam).join('\n')}
    <button onclick='startRegisterTeam()'>Register Team</button>`;
}

function printTeam(team) {
    return '<p>' + team.teamId + ': ' + team.teamName + '</p>';
}
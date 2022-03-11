let searchTerms = window.location.search;

fetchTeams();

function fetchTeams() {
    fetch('/teams' + searchTerms).then(resp => resp.json()).then(teams => {
        document.querySelector('#teams').innerHTML = printTeams(teams);
    });
}

function printTeams(json) {
    return `${json.map(printTeam).join('\n')}`;
}

function printTeam(team) {
    return '<p>' + team.teamId + ': ' + team.teamName + '</p>';
}


document.querySelector('h1').innerText = "App";

fetch('/team').then(resp => resp.json()).then(teams => {
    document.querySelector('#teams').innerHTML = listTeams(teams);
});

function listTeams(json) {
    return `${json.map(listTeam).join('\n')}`;
}

function listTeam(team) {
    return '<p>' + team.teamId + ': ' + team.teamName + '</p>';
}
function startAddTeam() {
    let form = `Team ID: <input type='number' id='newTeamId'></br>
    Team Name: <input type='text' id='newTeamName'></br>
    <button onclick='addTeam()'>Add</button>`
    document.querySelector('#addTeam').innerHTML = form;
}

function addTeam() {
    let newTeamId = document.getElementById('newTeamId').value
    let newTeamName = document.getElementById('newTeamName').value

    let team = {
        "teamId": newTeamId,
        "teamName": newTeamName
    }

    fetch('/teams', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(team)
    }).then(result => {
        if (result.status != 200) {
            throw new Error('Bad Server Response')
        }
    }).catch(error => { console.log(error) });

    fetchTeams();
}
function startRegisterTeam() {
    let form = `Team ID: <input type='number' id='registerTeamId'></br>
    <button onclick='registerTeam()'>Register</button>`;
    document.querySelector("#registerTeam").innerHTML = form;
}

function registerTeam() {
    let registerTeamId = document.getElementById("registerTeamId").value;
    let registerTeamObj;

    let registerEventId = window.location.search;

    if (registerTeamId != '') {
        fetch("/teams?teamId=" + registerTeamId).then(resp => resp.json()).then(teams => {
            registerTeamObj = teams[0];

            fetch('/registered' + registerEventId, {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(registerTeamObj)
            }).then(result => {
                if (result.status != 200) {
                    throw new Error('Bad Server Response');
                }
            }).catch(error => { console.log(error); });
        });
    }
}
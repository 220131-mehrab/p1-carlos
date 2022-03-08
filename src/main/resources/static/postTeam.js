function postTeam() {
    let team = {
        "teamId": document.getElementById("teamId").value,
        "teamName": document.getElementById("teamName").value
    }

    fetch('/team', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(team)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response")
        }
    }).catch((error) => { console.log(error); })

    fetch('/team').then(resp => resp.json()).then(teams => {
        document.querySelector('#teams').innerHTML = listTeams(teams);
    })
}
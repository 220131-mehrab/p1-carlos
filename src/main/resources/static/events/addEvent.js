function startAddEvent() {
    let form = `Event ID: <input type='number' id='newEventId'></br>
    Event Name: <input type='text' id='newEventName'></br>
    <button onclick='addEvent()'>Add</button>`
    document.querySelector('#addEvent').innerHTML = form;
}

function addEvent() {
    let newEventId = document.getElementById('newEventId').value
    let newEventName = document.getElementById('newEventName').value

    let event = {
        "eventId": newEventId,
        "eventName": newEventName
    }

    fetch('/events', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(event)
    }).then(result => {
        if (result.status != 200) {
            throw new Error('Bad Server Response')
        }
    }).catch(error => { console.log(error) });

    fetchEvents();
}
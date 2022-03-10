create table Team (
    teamId int not null,
    teamName varchar not null,
    constraint "PK_TEAM" primary key (teamId)
);

create table Event (
    eventId int not null,
    eventName varchar not null,
    constraint "PK_EVENT" primary key(eventId)
);

create table Registered (
    eventId int not null,
    teamId int not null,
    constraint "FK_EVENT_REG" foreign key (eventId) references Event,
    constraint "FK_TEAM_REG" foreign key (teamId) references Team
);

insert into Team values (148, 'RoboWranglers');
insert into Team values (118, 'Robonauts');
insert into Team values (2468, 'Team Appreciate');

insert into Event values (1, 'Austin');
insert into Event values (2, 'Pasadena');
insert into Event values (3, 'Dripping Springs');

insert into Registered values (1, 148);
insert into Registered values (1, 118);
insert into Registered values (2, 148);
insert into Registered values (2, 2468);
insert into Registered values (3, 118);
insert into Registered values (3, 2468);
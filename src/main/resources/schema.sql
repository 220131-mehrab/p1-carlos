create table Team (
    teamId int not null,
    teamName varchar not null,
    constraint "PK_TEAM" primary key (teamId)
);

insert into Team values (148, 'RoboWranglers');
insert into Team values (118, 'Robonauts');
insert into Team values (2468, 'Team Appreciate');
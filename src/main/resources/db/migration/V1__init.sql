create table member (
                        member_id bigint not null auto_increment,
                        created_at varchar(255),
                        updated_at varchar(255),
                        username varchar(255),
                        password varchar(255),
                        name varchar(255),
                        phone varchar(255),
                        birth varchar(255),
                        profile_image_url varchar(255),
                        authority varchar(255),
                        primary key (member_id)
) engine=InnoDB;

create table team (
                      team_id bigint not null auto_increment,
                      member_id bigint,
                      created_at varchar(255),
                      updated_at varchar(255),
                      name varchar(255),
                      sports varchar(255),
                      logo_image_url varchar(255),
                      history varchar(255),
                      intro varchar(255),
                      player_info varchar(255),
                      victory integer not null,
                      draw integer not null,
                      defeat integer not null,
                      primary key (team_id)
) engine=InnoDB;

create table matching_info (
                               matching_info_id bigint not null auto_increment,
                               created_at varchar(255),
                               updated_at varchar(255),
                               team_id bigint,
                               sports varchar(255),
                               date varchar(255),
                               place varchar(255),
                               title varchar(255),
                               content varchar(255),
                               status varchar(255),
                               primary key (matching_info_id)
) engine=InnoDB;

create table stadium (
                         stadium_id bigint not null auto_increment,
                         created_at varchar(255),
                         updated_at varchar(255),
                         name varchar(255),
                         image_url varchar(255),
                         phone varchar(255),
                         location varchar(255),
                         open_time varchar(255),
                         close_time varchar(255),
                         primary key (stadium_id)
) engine=InnoDB;

create table reservation_info (
                                  reservation_info_id bigint not null auto_increment,
                                  created_at varchar(255),
                                  updated_at varchar(255),
                                  team_id bigint,
                                  stadium_id bigint,
                                  date varchar(255),
                                  primary key (reservation_info_id)
) engine=InnoDB;

alter table team
    add constraint FKdyddawruqdwvuy3n6kgmc4q4j
    foreign key (member_id)
    references member (member_id);

alter table matching_info
    add constraint FK26gwd99fver09mu1e2gvgx1ay
    foreign key (team_id)
    references team (team_id);

alter table reservation_info
    add constraint FKegfvapj972404cgsi3g6dcm5s
    foreign key (team_id)
    references team (team_id);

alter table reservation_info
    add constraint FK9yli4hmkq5elrbyqfwa1psfhp
    foreign key (stadium_id)
    references stadium (stadium_id);
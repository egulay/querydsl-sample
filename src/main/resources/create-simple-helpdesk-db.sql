drop database if exists `simple_helpdesk`;
create database `simple_helpdesk`;

use `simple_helpdesk`;

create table helpdesk_user
(
  id        bigint auto_increment
    primary key,
  uuid      varchar(100)         not null,
  name      varchar(100)         not null,
  shoe_size int null,
  active tinyint(1) default 1 not null,
  constraint uq_user_uuid
    unique (uuid)
)
  charset = utf8;

create table helpdesk_request
(
  id      bigint auto_increment
    primary key,
  uuid    varchar(100)                       not null,
  user_id bigint                             not null,
  body    text                               not null,
  type    enum ('change', 'incident', 'bug') not null,
  constraint uq_request_uuid
    unique (uuid),
  constraint fk_request_user_id
    foreign key (user_id) references helpdesk_user (id)
      on delete cascade
)
  charset = utf8;

create table helpdesk_response
(
  id         bigint auto_increment
    primary key,
  uuid       varchar(100) not null,
  request_id bigint       not null,
  user_id    bigint       not null,
  body       text         not null,
  constraint uq_response_uuid
    unique (uuid),
  constraint fk_request_id
    foreign key (request_id) references helpdesk_request (id)
      on delete cascade,
  constraint fk_response_user_id
    foreign key (user_id) references helpdesk_user (id)
)
  charset = utf8;

insert into helpdesk_user (uuid, name)
VALUE (UUID(), 'Emre Gulay');
insert into helpdesk_user (uuid, name)
VALUE (UUID(), 'Benny Hill');
insert into helpdesk_user (uuid, name)
VALUE (UUID(), 'Daenerys Targaryen');

insert into user_tb(username, password, email, created_at) values('ssar', '$2a$10$bsz7FKEqhtD22rJwvphQU.PwZ4XV4XtZjqS6q1QtmiK3mxa/60vlO', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at) values('cos', '$2a$10$bsz7FKEqhtD22rJwvphQU.PwZ4XV4XtZjqS6q1QtmiK3mxa/60vlO', 'cos@nate.com', now());

insert into board_tb(title, content, user_id, created_at) values('제목1', '내용1', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목2', '내용2', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목3', '내용3', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목4', '내용4', 2, now());
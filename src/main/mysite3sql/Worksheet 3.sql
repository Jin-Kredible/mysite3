insert into users values('1', '1', '1', sysdate);

rollback;

select*from users;

select user_id from users where user_id='asdfasd';

select*from blog;

insert into blog (title, logo) values('dd', 'ss') where user_id='123';

update blog set title='d', logo='d' where user_id='123';
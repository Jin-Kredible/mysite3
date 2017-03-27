select*from category;
select*from post;
select*from users;
select*from blog;

delete category;
delete post;
delete users;
delete blog;

drop sequence seq_category;
create sequence seq_category start with 1 increment by 1 maxvalue 9999999;

select*from blog;

create sequence seq_post start with 1 increment by 1 maxvalue 99999999;
select*from post;


select rownum, cat_no as catNo, name as catName, description as catDesc from category where user_id='1';

select ro




insert into category(no, name) values(seq_category.nextval, 'd', 'd', sysdate, '123') ;

select*from users;

insert into blog(user_id) values ('1111');


select*from blog;
select*from category;



commit;


select cat_no as catNo, name as catName, description as catDesc from category where user_id=123;

select * from post where cat_no=9;


select* from post where cat_no=9;
select max(rownum) from post;

select*from category;
select*from blog;

select rownum, post_no as postNo, title as postTitle, content postContent, pub_date as postPubDate from post where cat_no='9';



select  post_no as postNo, title as postTitle, content postContent, pub_date as postPubDate, 
cat_no as catNo from post where cat_no=max(cat_no);

select a.title from post a, category b where post_no =
(select max(post_no) from post where cat_no=(select max(cat_no) from category));

select max(post_no) from post where cat_no=(select max(cat_no) from category where category.USER_ID='a');

select max(cat_no) from category where category.USER_ID='s';

select * from post where post_no=(select max(post_no) from post, category where category.USER_ID='s');
select*from post;
select*from category;

select max(post_no) from post, category where category.USER_ID='s';

select post.title as postTitle, post.pub_date as postPubDate from post where post.cat_no=(select max(post.cat_no) from post, category where category.USER_ID='s');

select post.title as postTitle, post.pub_date as postPubDate from post where post.pub_date=;

select post.title as postTitle, post.pub_date as postPubDate from post where
 post.pub_date='2017-03-06 16:13:28';
 
 select title as postTitle, content as postContent, to_char(pub_date,'yyyy/mm/dd/hh/mm/ss') as postPubDate from post where post_no=
 (select max(post_no) from post, category where category.USER_ID='s');
 
 select b.title as postTitle, b.content as postContent, to_char(b.pub_date,'yyyy/mm/dd/hh/mm/ss') as 
 	postPubDate from category a, post b where post_no=(select max(b.post_no) from post where category.USER_ID='s') and b.CAT_NO =a.cat_no;
 
 select max(post_no) from post, category where category.USER_ID='s';
 
 select*from post where post.CAT_NO = (
 select cat_no from post where post_no = '10');
 
 select title as postTitle, content as postContent, post_no as postNo, pub_date from post where post_no=(select max(post_no) from post, category where category.USER_ID='s');
 
 
 select title as postTitle, content as postContent, post_no as postNo from post where post_no=(select max(post_no) 
 from post, category where category.USER_ID='a');
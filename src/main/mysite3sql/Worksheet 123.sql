SELECT cat_no AS catNo
  FROM category
 WHERE user_id = 'a' AND TRIM (name) = 'Third One';

SELECT '----' || name || '----'
  FROM category;

SELECT title
  FROM blog
 WHERE user_id = 'a';

SELECT * FROM blog;

ALTER TABLE blog
   MODIFY (logo NULL);

SELECT title AS postTitle, content AS postContent, post_no AS postNo
  FROM POST
 WHERE post_no = (SELECT MAX (post_no)
                    FROM POST, category
                   WHERE category.USER_ID = '12');

SELECT * FROM category;

SELECT * FROM POST;

SELECT MAX (POST.post_no)
  FROM POST, category
 WHERE POST.CAT_NO = category.CAT_NO AND category.USER_ID = 'a';

SELECT *
  FROM POST, category
 WHERE POST.CAT_NO = category.CAT_NO;

SELECT ROWNUM,
       catNo,
       catName,
       catDesc
  FROM (  SELECT cat_no AS catNo, name AS catName, description AS catDesc
            FROM category
           WHERE user_id = 'a'
        ORDER BY pub_date DESC);

  SELECT name AS catName, cat_no AS catNo
    FROM category
   WHERE user_id = 'a'
ORDER BY pub_date DESC;

DELETE FROM category
      WHERE cat_no = 22;

ROLLBACK;

  SELECT post_no AS postNo,
         title AS postTitle,
         content postContent,
         pub_date AS postPubDate,
         cat_no AS catNo
    FROM POST
   WHERE cat_no = '15'
ORDER BY pub_date DESC;

select post_no as postNo, title as postTitle, content postContent, pub_date as postPubDate, cat_no as catNo 
from post where cat_no=#{catNo} order by pub_date desc;



SELECT title AS postTitle, content AS postContent
  FROM POST
 WHERE cat_no = 14 AND post_no = 14;

SELECT title AS postTitle,
       content AS postContent,
       pub_date AS postPubDate,
       POST.cat_no AS catNo
  FROM POST
 WHERE POST.CAT_NO = (SELECT cat_no
                        FROM POST
                       WHERE post_no = '13');

SELECT title AS postTitle,
       content AS postContent,
       pub_date AS postPubDate,
       POST.cat_no AS postNo,
       post_no AS postNo
  FROM POST
 WHERE POST.CAT_NO = (SELECT cat_no
                        FROM POST
                       WHERE post_no = 13);
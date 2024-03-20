# API_Signature

## Bloopers (Shorts)
- GET
  - api/bloopers (Bloopers 조회)
  - api/bloopers/{contents-id}/comments (댓글 조회)
  
- POST
  - api/bloopers/{contents-id}/comments (댓글 작성)
  - api/bloopers/{contents-id}/like
  - api/bloopers/{contents-id}/unlike

- PATCH
  - api/bloopers/{contents-id} (게시물 수정)
  - 
- DELETE
  - api/bloopers/{contents-id} (게시물 삭제)
  - api/bloopers/{contents-id}/comments/{id}


## Home (Feed)

- GET
  - api/feeds (피드 조회)
  - api/feeds/{portfolio_id} (포트폴리오 조회)

- POST
  - api/feeds (포트폴리오 업로드)
  - api/feeds/{portfolio_id}/like (좋아요)
  - api/feeds/{portfolio_id}/unlike (좋아요 취소)

- PATCH
  - api/feeds/{portfolio_id} (포트폴리오 수정)

- DELETE
  - api/feeds/{portfolio_id} (포트폴리오 삭제)

## Profile

- GET 
  - api/users/{user_id} (프로필 조회)
  - api/users/{user_id}/portfolio
  - api/users/{user_id}/blooper 
  - api/users/{user_id}/follower

- POST
  - api/users/{user_id}/follow
  - api/users/{user_id}/unfollow
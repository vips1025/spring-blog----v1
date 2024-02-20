package shop.mtcoding.blog.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;
        private Boolean boardOwner;

        public void isBoardOwner(User sessionUser){
            if(sessionUser == null) boardOwner = false;
            else boardOwner = sessionUser.getId() == userId;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ReplyDTO {
        private Integer id;
        private Integer userId;
        private String comment;
        private String username;

        public ReplyDTO(Object[] ob) {
            this.id = (Integer) ob[0];
            this.userId = (Integer) ob[1];
            this.comment = (String) ob[2];
            this.username = (String) ob[3];
        }
    }
}

import { CommentListItem } from "types/interface";

export default interface GetCommentListResponseDto extends Response {
    commentList: CommentListItem[]
}
import React, { useEffect, useState } from 'react'
import './style.css'
import FavoriteItem from 'components/FavoriteItem'
import { Board, CommentListItem, FavoriteListItem } from 'types/interface'
import { commentListMock, favoriteListMock } from 'mocks'
import CommentItem from 'components/CommentItem'
import Pagination from 'components/Pagination'
import defaultProfileImage from 'assets/image/user.png'
import { useLoginUserStore } from 'stores'
import { useNavigate, useParams } from 'react-router-dom'
import { USER_PATH } from 'constant'

// component: 게시물 상세 화면 컴포넌트 //
export default function BoardDetail() {

    // state: 게시물 번호 path variable 상태       //
    const { boardNumber } = useParams();
    // state: 로그인 유저 상태                     //
    const { loginUser } = useLoginUserStore();

    // function: 네비게이트 함수     //
    const navigator = useNavigate();

    // component: 게시물 상세 상단 컴포넌트 //
    const BoardDetailTop = () => {

      // state : //
      const [board, setBoard] = useState<Board | null>(null)
      // state: more 버튼 상태     //
      const [showMore, setShowMore] = useState<boolean>(false)

      // event handler: 닉네임 클릭 이벤트 처리   //
      const onNicknameClickHandler = () => {
        if (!board) return;
        navigator(USER_PATH(board.writerEmail))
      }

      // event handler: more 버튼 클릭 이벤트 처리   //
      const onMoreButtonClickHandler = () => {
        setShowMore(!showMore);
      }

      // render: 게시물 상세 상단 컴포넌트 랜더링 //
      if (!board) return <></>
      return (
        <div id='board-detail-top'>
          <div className='board-detail-top-header'>
            <div className='board-detail-title'>{'오늘 점심 뭐먹지 맛있는거 먹고 싶다.'}</div>
            <div className='board-detail-top-sub-box'>
              <div className='board-detail-write-info-box'>
                <div className='board-detail-writer-profile-image' style={{ backgroundImage: `url(${board?.writerProfileImage ? board.writerProfileImage : defaultProfileImage})`}}></div>
                <div className='board-detail-writer-nickname' onClick={onNicknameClickHandler}>{'nickname'}</div>
                <div className='board-detail-info-divider'>{'\|'}</div>
                <div className='board-detail-write-date'>{'2022. 05. 12.'}</div>
              </div>
              <div className='icon-button' onClick={onMoreButtonClickHandler}>
                <div className='icon more-icon'></div>
              </div>
              {showMore &&
              <div className='board-detail-more-box'>
                <div className='board-detail-update-button'>{'수정'}</div>
                <div className='divider'></div>
                <div className='board-detail-delete-button'>{'삭제'}</div>
              </div>
              }
            </div>
          </div>
          <div className='divider'></div>
          <div className='board-detail-top-main'>
            <div className='board-detail-main-text'>{'오늘 점심 뭐먹을 지 너무 고민이 되는데 뭐 먹을까? 나 점심 때'}</div>
            <img className='board-detail-main-image' src='https://i.pinimg.com/originals/b8/fc/12/b8fc120f8fd33f3b2f2e4417c1343987.jpg' />
          </div>
        </div>
      )
    }

    // component: 게시물 상세 하단 컴포넌트 //
    const BoardDetailBottom = () => {
      const [favoriteList, setFavoriteList] = useState<FavoriteListItem[]>([])
      const [commentList, setCommentList] = useState<CommentListItem[]>([])

      useEffect(()  => {
        setFavoriteList(favoriteListMock)
        setCommentList(commentListMock)
      }, [])

      // render: 게시물 상세 하단 컴포넌트 랜더링 //
      return (
        <div id='board-detail-bottom'>
          <div className='board-detail-bottom-button-box'>
            <div className='board-detail-bottom-button-group'>
              <div className='icon-button'>
                <div className='icon favorite-fill-icon'></div>
              </div>
              <div className='board-detail-bottom-button-text'>{`좋아요 ${12}`}</div>
              <div className='icon-button'>
                <div className='icon up-light-icon'></div>
              </div>
            </div>
            <div className='board-detail-bottom-button-group'>
              <div className='icon-button'>
                <div className='icon comment-icon'></div>
              </div>
              <div className='board-detail-bottom-button-text'>{`댓글 ${12}`}</div>
              <div className='icon-button'>
                <div className='icon up-light-icon'></div>
              </div>
            </div>
          </div>
          <div className='board-detail-bottom-favorite-box'>
            <div className='board-detail-bottom-favorite-container'>
              <div className='board-detail-bottom-favorite-title'>{'좋아요 '}<span className='emphasis'>{12}</span></div>
              <div className='board-detail-bottom-favorite-contents'>
                {favoriteList.map(item => <FavoriteItem favoriteListItem={item} />)}
              </div>
            </div>
          </div>
          <div className='board-detail-bottom-comment-box'>
            <div className='board-detail-bottom-comment-container'>
              <div className='board-detail-bottom-comment-title'>{'댓글 '}<span className='emphasis'>{12}</span></div>
              <div className='board-detail-bottom-comment-list-container'>
                {commentList.map(item => <CommentItem commentListItem={item} />)}
              </div>
            </div>
            <div className='divider'></div>
            <div className='board-detail-bottom-comment-pagination-box'>
              <Pagination />
            </div>
            <div className='board-detail-bottom-comment-input-box'>
              <div className='board-detail-bottom-comment-input-container'>
                <textarea className='board-detail-bottom-comment-textarea' placeholder='댓글을 작성해주세요.' />
                <div className='board-detail-bottom-comment-button-box'>
                  <div className='disable-button'>{'댓글달기'}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      )
    }

    // render: 게시물 상세 화면 컴포넌트 랜더링 //
    return (
      <div id='board-detail-wrapper'>
        <div className='board-detail-container'> 
          <BoardDetailTop />
          <BoardDetailBottom />
        </div>
      </div>
    )
}

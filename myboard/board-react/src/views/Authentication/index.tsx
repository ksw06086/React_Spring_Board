import React, { useState } from 'react'
import './style.css'
import InputBox from 'components/InputBox';

// component: 인증 화면 컴포넌트 //
export default function Authentication() {

    // state: 화면 상태 //
    const [view, setView] = useState<'sign-in' | 'sign-up'>('sign-in');

    // component: sign in card 컴포넌트 //
    const SignInCard = () => {

        // state: 이메일 상태 //
        const [email, setEmail] = useState<string>('');
        // state: 패스워드 상태 //
        const [password, setPassword] = useState<string>('');
        // state: 패스워드 타입 상태 //
        const [passwordType, setPasswordType] = useState<'text' | 'password'>('password');
        // state: 에러 상태 //
        const [error, setError] = useState<boolean>(false);
        // state: 패스워드 버튼 아이콘 상태 //
        const [passwordButtonIcon, setPasswordButtonIcon] = useState<string>('')

        // render: sign in card 컴포넌트 랜더링 //
        return (
            <div className='auth-card'>
                <div className='auth-card-box'>
                    <div className='auth-card-top'>
                        <div className='auth-card-title-box'>
                            <div className='auth-card-title'>{'로그인'}</div>
                        </div>
                        <InputBox label='이메일 주소' type='text' placeholder='이메일 주소를 입력해주세요.' error={error} value={email} setValue={setEmail} onKeyDown={} />
                        <InputBox label='패스워드' type={passwordType} placeholder='비밀번호를 입력해주세요.' error={error} value={password} setValue={setPassword} icon={} onButtonClick={} onKeyDown={} />
                    </div>
                    <div className='auth-card-bottom'>
                        <div className='auth-sign-in-error-box'>
                            <div className='auth-sign-in-error-message'>
                                {'이메일 주소 또는 비밀번호를 잘못 입력했습니다.\n입력하신 내용을 다시 확인해주세요.'}
                            </div>
                        </div>
                        <div className='black-large-full-button'>{'로그인'}</div>
                        <div className='auth-description-box'>
                            <div className='auth-description'>{'신규 사용자이신가요? '}<span className='auth-description-link'>{'회원가입'}</span></div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    // component: sign up card 컴포넌트 //
    const SignUpCard = () => {

        // render: sign up card 컴포넌트 랜더링 //
        return (
            <div className='auth-card'></div>
        )
    }

    // render: 인증 화면 컴포넌트 랜더링 //
    return (
        <div id='auth-wrapper'>
            <div className='auth-container'>
                <div className='auth-jumbotron-box'>
                    <div className='auth-jumbotron-contents'>
                        <div className='auth-logo-icon'></div>
                        <div className='auth-jumbotron-text-box'>
                            <div className='auth-jumbotron-text'>{'환영합니다.'}</div>
                            <div className='auth-jumbotron-text'>{'COMPANY BOARD 입니다.'}</div>
                        </div>
                    </div>
                </div>
                {view === 'sign-in' && <SignInCard />}
                {view === 'sign-up' && <SignUpCard />}
            </div>
        </div>
    )
}

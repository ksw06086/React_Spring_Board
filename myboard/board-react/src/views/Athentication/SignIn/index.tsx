import { TextField, Box, Card, CardActions, CardContent, Button } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react'

export default function SignIn() {
    const [userEmail, setUserEmail] = useState<string>('');
    const [userPassword, setUserPassword] = useState<string>('');

    const signInHandler = () => {
        if(userEmail.length === 0 || userPassword.length === 0){
            alert('이메일과 비밀번호를 입력하세요.');
            return;
        }

        const data = {
            userEmail,
            userPassword
        }

        axios.post('http://localhost:4000/api/auth/signin', data)
        .then((res) => {
            const responseData = res.data;
            console.log(responseData);
            if(!responseData.result){
                console.log('로그인에 실패했습니다.');
                return;
            }
            const { token, exprTime } = responseData.data;
        })
        .catch((err) => {
            alert('로그인에 실패했습니다.');
        });
    }

    return (
        <Card sx={{ minWidth: 275, maxWidth: "50vw" }}>
            <CardContent>
                <Box>
                    <TextField
                        fullWidth
                        label="이메일"
                        type='email'
                        variant='standard'
                        onChange={(e)=>{setUserEmail(e.target.value)}}
                    />
                    <TextField
                        fullWidth
                        label="비밀번호"
                        type='password'
                        variant='standard'
                        onChange={(e)=>{setUserPassword(e.target.value)}}
                    />
                </Box>
            </CardContent>
            <CardActions>
                <Button fullWidth variant="contained" onClick={()=>{ signInHandler(); }}>로그인</Button>
            </CardActions>
        </Card>
    )
}
